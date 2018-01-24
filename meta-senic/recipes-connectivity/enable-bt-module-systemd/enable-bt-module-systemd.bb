SUMMARY = "Service responsible for attaching the BT module at startup"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
 

REQUIRED_DISTRO_FEATURES= "systemd"

SRC_URI += "file://btattach-usb-PCA10040.service \
            file://enable-bt-module.service \
	    file://enable-bt-module"

# Only this one is actually enabled as the systemd
# service. btattach-usb-PCA10040 is disabled by default.
SYSTEMD_SERVICE_${PN} = "enable-bt-module.service "

# This was added to avid the WARNING:
# Files/directories were installed but not shipped in any package
FILES_${PN} += "${systemd_system_unitdir}/btattach-usb-PCA10040.service"

inherit systemd

do_install () {

    install -d ${D}${systemd_system_unitdir}
    install -d ${D}${bindir}
    install -m 0644 ${WORKDIR}/enable-bt-module.service ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/btattach-usb-PCA10040.service ${D}${systemd_system_unitdir}
    install -m 0755 ${WORKDIR}/enable-bt-module ${D}${bindir}
}


# [Alan]
# This seems to end up installing the service to both
# etc/systemd/system/multi-user.target.wants/btattach.service
# and lib/systemd/system/btattach.service
# Not sure if the second one is required, could be dropped to
# not cause confusion

