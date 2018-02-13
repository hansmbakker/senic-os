#!/bin/bash
# RESULTS: F indicates Failure, P being Pass.
# Test Sequence :
# * Finding connected BLE interface.
# * Scanning for other devices.
# * Being able to connect to available devices.
# * Exchange data with connected device(device info).
# * Disconnecting to connected device.

print_logs() {
    if [ "$1" == "ERROR" ]; then
        echo -e "\033[1m************************************************************************\033[0m"
        echo -e "\033[1m $2 \033[0m"
        echo -e "\033[1m************************************************************************\033[0m"
    else
        echo "$1"
    fi
}

BLE_DEVICES="$(hciconfig dev | grep Type | wc -l)"
BLE_FOUND="F"
if [ $BLE_DEVICES -gt 0 ]; then
    print_logs " $BLE_DEVICES Bluetooth device(s) found"
    BLE_FOUND="P"
else
    print_logs "ERROR" "No Bluetooth device was found.
 Please attach one and re-run the tests."
fi

# Restarting BLE device service, it helped with BLE operations
systemctl restart bluetooth
systemctl restart enable-bt-module

echo
echo -e "\033[1m************************************************************************\033[0m"
echo -e "\033[1mPlease make sure you have Bluetooth enabled devices in vicinity.\033[0m"
echo -e "\033[1mOr else following tests WILL FAIL despite possibly working BT module.\033[0m"
echo -e "\033[1m************************************************************************\033[0m"
echo
CAN_SCAN="F"
CAN_CONNECT="F"
CAN_DISCONNECT="F"
CAN_TX_DATA="F"
while IFS='': read interface_line; do
    interface=$(echo $interface_line | cut -d':' -f1)
    print_logs " Scanning using $interface interface"
    status=$(hciconfig $interface | sed -n '3 p' | cut -d' ' -f1 | xargs)
    if [ "$status" == "DOWN" ]; then
        hciconfig $interface up
    fi
    while IFS='': read line; do
        CAN_SCAN="P"
        mac=$(echo $line | cut -d' ' -f1)
        name=$(echo $line | cut -d' ' -f2-)
        print_logs " Found device named: $name with mac address: $mac"
        # To connect to the device
        hcitool -i $interface cc $mac 2>&1
        if [ "$(hcitool con | grep $mac | wc -l)" -gt 0 ]; then
            print_logs " Successfully established Bluetooth connection with $name"
            CAN_CONNECT="P"
        else
            print_logs "ERROR" " Failed to connect to device: $name"
            continue
        fi
        info=$(hcitool info $mac)
        if [ "$(echo $info | grep "$mac" | wc -l)" -gt 0 ] && [ "$(echo $info | grep Features | wc -l)" -gt 0 ] ; then
            print_logs " Successfully enquired info for device: $name"
            CAN_TX_DATA="P"
        fi
        # Disconnecting from the device
        hcitool -i $interface dc $mac 2>&1
        if [ "$(hcitool con | grep $mac | wc -l)" -eq 0 ]; then
            CAN_DISCONNECT="P"
            print_logs " Successfully disconnected with $name"
        else
            print_logs "ERROR" " Failed to disconnect to $name."
        fi
    done < <(hcitool -i $interface scan | sed -n '1!p')
done < <(hciconfig dev | grep Type)

echo "$BLE_FOUND $CAN_SCAN $CAN_CONNECT $CAN_TX_DATA $CAN_DISCONNECT" >> results.txt
