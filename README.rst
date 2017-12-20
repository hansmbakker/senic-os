SenicOS -- The Senic Linux distribution
=======================================

Well, first of all, admittedly "SenicOS" is a bit of a mouthful - it's not actually a custom operating system, but simply a `yocto <https://www.yoctoproject.org/>`_ based **linux distribution**.

You can `find the sources on github <https://github.com/getsenic/senic-os>`_.

To bootstrap a fresh checkout of this repo make sure you are running this on an `OS supported by OpenEmbedded as a build platform <http://www.yoctoproject.org/docs/1.8/ref-manual/ref-manual.html#required-packages-for-the-host-development-system>`_ and then simply run ``make``.
This will check out the required upstream yocto repositories.

You can then perform a build with these settings on the host like so::

    source oe/oe-init-build-env


Debugging
---------

Run ``bitbake -c do_rootfs senic-os-dev`` to trigger a build.
Then you can check ``tmp-glibc/work/senic_hub-senic-linux-gnueabi/senic-os-dev/1.0-r0/rootfs/`` and inspect what the filesystem looks like.
In many cases this is enough and you can save yourself the trouble of writing the image to disk and physically boot into it.

To mount the finished image directly on the build host (i.e. to avoid downloading, burning and booting it) in situations where it's not enough to inspect the rootfs (i.e. when debugging the partitioning layout).

First examine the image::

  fdisk -l -u tmp-glibc/deploy/images/senic-hub-beta/senic-os-dev-senic-hub-beta.sdimg 

Then mount the desired partition using the start sector as offset like below::

  sudo mount -o loop,offset=$((512*2080768)) tmp-glibc/deploy/images/senic-hub-beta/senic-os-dev-senic-hub-beta.sdimg sdcard


Configuring the wifi network manually
-------------------------------------

Normally, a freshly booted instance will come up in onboarding mode, meaning you can connect to and configure it via the app.

However, you can also connect via a serial console (i.e. ``screen /dev/tty.usbserial 115200``), login as root (without password) and connect to a wireless network like so::


-    nmcli dev wifi con <SSID> password <PASSWORD> ifname wlan0


Hardware
--------

The reset button is connected to pin 10 on the GPIO connector. When the button is pressed the GPIO pin is pulled to GND, otherwise it's pulled to SYS_3.3V.
