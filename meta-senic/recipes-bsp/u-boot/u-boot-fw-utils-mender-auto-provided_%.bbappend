#DEPENDS += "swig-native"

COMPATIBLE_MACHINE = "(senic-hub)"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Mender integration
PROVIDES += "u-boot-fw-utils"
RPROVIDES_${PN} += "u-boot-fw-utils"
BOOTENV_SIZE = "0x20000"

SRC_URI += "file://boot.cmd \
	file://0001-add-senic-hub-dts-and-defconfig.patch \
 	"

SRC_URI_remove = "file://0003-Integration-of-Mender-boot-code-into-U-Boot.patch"
SRC_URI_append = " file://0001-Integration-of-Mender-boot-code-into-U-Boot.patch"
