DESCRIPTION="Upstream's U-boot configured for sunxi devices"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "dtc-native bc-native swig-native"

COMPATIBLE_MACHINE = "(senic-hub)"

SRCBRANCH = "v2017.09"

SRC_URI = "git://git.denx.de/u-boot.git;protocol=git; \
	file://boot.cmd \
	file://0001-add-senic-hub-dts-and-defconfig.patch \
	"

SRCREV = "c98ac3487e413c71e5d36322ef3324b21c6f60f9"
PV = "git-${SRCBRANCH}"
S = "${WORKDIR}/git"

SPL_BINARY="u-boot-sunxi-with-spl.bin"

UBOOT_ENV_SUFFIX = "scr"
UBOOT_ENV = "boot"

# This symlink is required for u-boot v2017.09 to build
do_compile_prepend() {
    if [ ! -f ${TMPDIR}/hosttools/x86_64-linux-gnu-gcc ]; then
        ln -s ${TMPDIR}/hosttools/gcc ${TMPDIR}/hosttools/x86_64-linux-gnu-gcc
    fi
}

do_compile_append() {
    ${B}/tools/mkimage -C none -A arm -T script -d ${WORKDIR}/boot.cmd ${WORKDIR}/${UBOOT_ENV_BINARY}
}
