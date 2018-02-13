#!/bin/bash
# RESULTS: F indicates Failure, P being Pass.
# Test Sequence :
# * Looking for MediaTek wifi USB device.
# * Confirming that usb gets listed as wifi.
# * Scanning for available wifi nerworks.
# * Confirm that we are able to connect to $SSID_NETWORK.
# * Fetch a html page using wifi interface.
# * Disconnecting to $SSID_NETWORK.

print_logs() {
    if [ "$1" == "ERROR" ]; then
        echo -e "\033[1m************************************************************************\033[0m"
        echo -e "\033[1m $2 \033[0m"
        echo -e "\033[1m************************************************************************\033[0m"
    else
        echo "$1"
    fi
}

USB_LIST="$(usb-devices)"
IS_WLAN="$(echo "$USB_LIST" | grep -iE 'mt7601u' | wc -l)"

WIFI_HW_FOUND="F"
# Check if WiFi dongle is found
if [ $IS_WLAN -gt 0 ]; then
    print_logs " WLAN Dongle is found"
else
	print_logs "ERROR" "Please plugin wifi usb dongle and rerun the tests."
fi


# Check wifi interfaces using nmcli
WIFI_DRIVER="F"
WIFI_INTERFACES="$(nmcli -t -f common device show | grep wifi | wc -l)"
if [ $WIFI_INTERFACES -gt 0 ]; then
	echo " wifi interfaces are available";
    WIFI_DRIVER="P"
    while IFS=':': read -r name interface status ssid;
    do
        print_logs " Name of interface: $name";
    done < <(nmcli -t -f DEVICE,TYPE,STATE,CONNECTION dev | grep 'wifi')
else
    print_logs "ERROR" "Drivers for the usb wifi dongle are missing."
fi

CAN_SCAN="F"
CAN_CONNECT="F"
CAN_DISCONNECT="F"
CAN_TX_DATA="F"
# Confirm that we are able to scan available networks
SCANNED_SSIDS="$(nmcli -t -f SSID dev wifi list | wc -l)"
if [ $SCANNED_SSIDS -gt 0 ]; then
	print_logs " Successfully scanned $SCANNED_SSIDS networks"
    CAN_SCAN="P"
else
	print_logs "ERROR" "Wifi networks were missing.
 Please confirm that routers are switched on and in vicinity."
fi

# Look for SSID details in environment variables
if [ -n "$SSID_NAME" ]; then
    while IFS=':': read -r name interface status ssid;
    do
        if [ "$status" == "connected" ]; then
            print_logs " Interface: $name is already connected to $ssid";
        else
            print_logs " Connecting $name to $SSID_NAME..."
            # nmcli command to connect to network
            nmcli dev wifi con "$SSID_NAME" password "$SSID_PASSWORD" ifname $name
            updated_status=$(nmcli -t -f DEVICE,TYPE,STATE,CONNECTION dev | grep $name | awk -F':' '{print $3}')
            if [ "$updated_status" == "connected" ]; then
                CAN_CONNECT="P"
                connection_name=$(nmcli -t -f DEVICE,TYPE,STATE,CONNECTION dev | grep $name | awk -F':' '{print $4}')
                print_logs " Interface: $name is now connected to $SSID_NAME"
                # logic to fetch data using interface
                status_code=$(curl --interface $name --write-out %{http_code} --silent --output /dev/null https://www.senic.com/en/)
                server_name=$(curl --interface $name -I -s https://www.senic.com/en/ | grep server | cut -d' ' -f2 | tr -d '[:space:]')
                if [ $status_code -eq 200 ] && [ "$server_name" == "Netlify" ]; then
                    print_logs " Successfully fetched html using interface $name"
                    CAN_TX_DATA="P"
                else
                    print_logs "ERROR" "Failed to fetch data using interface $name"
                fi
                # nmcli command to close down a connection.
                nmcli con down id "$connection_name"
                disconnect_status=$(nmcli -t -f DEVICE,TYPE,STATE,CONNECTION dev | grep $name | awk -F':' '{print $3}')
                if [ "$disconnect_status" == "disconnected" ]; then
                    CAN_DISCONNECT="P"
                    nmcli con delete "$connection_name"
                    print_logs " Closed connection for: $name"
                else
                    print_logs "ERROR" "Failed to close connection for: $name"
                fi
            else
                print_logs "ERROR" "Failed to connect Interface: $name to $ssid
 Please confirm that name and credentials are correct."
            fi
        fi
    done < <(nmcli -t -f DEVICE,TYPE,STATE,CONNECTION dev | grep 'wifi')
else
    print_logs "ERROR" "Please set SSID_NAME and SSID_PASSWORD environment variables
 $ export SSID_NAME=wifi network name
 $ export SSID_PASSWORD=wifi password
 and rerun the tests"
fi

echo "$WIFI_HW_FOUND $WIFI_DRIVER $CAN_SCAN $CAN_CONNECT $CAN_TX_DATA $CAN_DISCONNECT" >> results.txt
