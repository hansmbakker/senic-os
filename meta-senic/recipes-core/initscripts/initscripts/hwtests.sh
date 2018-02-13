#!/bin/bash
### BEGIN INIT INFO
# Provides: basic_hw_test_suit
# Short-Description: Test suite for different HW components
### END INIT INFO

if [ "$1" == "--h" ]; then
    echo "Usage: `basename $0`"
    echo ""
    echo "For wifi tests, you need to set and export SSID_NAME and SSID_PASSWORD environment variables."
    echo ""
    exit 0
fi

# Test for LED, Manual prompt for now, given that GPIO6, being part of
# device tree, is locked from userspace.
LED_TEST="F"
while true; do
    read -p "Is the status LED On? y/n: " led_status
    case $led_status in
    [Yy]* )
        LED_TEST="P"
        break;;
    [Nn]* )
        echo " Failed LED test"
        break;;
    * )
        echo " Please answer y/n.";;
    esac
done

echo "$LED_TEST" > results.txt
dir=$(dirname "$0")

/bin/bash "$dir/bluetooth_test.sh"
/bin/bash "$dir/wifi_test.sh"


HW_FAILS="$(cat results.txt | grep F | wc -l)"
if [ $HW_FAILS -gt 0 ]; then
    echo -e "\033[1m************************************************************************\033[0m"
    echo -e "\033[1m Failed Hardware tests. \033[0m"
    echo -e "\033[1m************************************************************************\033[0m"
else
    cat /proc/cpuinfo | grep Serial | cut -d' ' -f2 | qrencode -t ANSI256 -l H
fi
