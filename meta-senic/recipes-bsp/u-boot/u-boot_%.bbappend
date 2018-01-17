DEPENDS += "swig-native"

COMPATIBLE_MACHINE = "(senic-hub)"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Mender integration
MENDER_UBOOT_AUTO_CONFIGURE = "0"
PROVIDES += "u-boot"
RPROVIDES_${PN} += "u-boot"
BOOTENV_SIZE = "0x20000"

SRC_URI += "file://boot.cmd \
	file://0001-add-senic-hub-dts-and-defconfig.patch \
 	"

SRC_URI_remove = "file://0003-Integration-of-Mender-boot-code-into-U-Boot.patch"
SRC_URI_append = " file://0001-Integration-of-Mender-boot-code-into-U-Boot.patch"

SPL_BINARY="u-boot-sunxi-with-spl.bin"

UBOOT_ENV_SUFFIX = "scr"
UBOOT_ENV = "boot"

# This symlink is required for u-boot v2017.09 to build
do_compile_prepend() {
  if [ ! -f ${TMPDIR}/hosttools/x86_64-linux-gnu-gcc ]; then
    ln -s ${TMPDIR}/hosttools/gcc ${TMPDIR}/hosttools/x86_64-linux-gnu-gcc
  fi
}

do_compile_append() {
  ${B}/tools/mkimage -C none -A arm -T script -d ${WORKDIR}/boot.cmd ${WORKDIR}/${UBOOT_ENV_BINARY}
}
