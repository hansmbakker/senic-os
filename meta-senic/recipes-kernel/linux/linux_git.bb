SECTION = "kernel"
DESCRIPTION = "Mainline Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "(senic-hub)"

inherit kernel

require linux.inc

# Default is to use stable kernel version
# If you want to use latest git version set to "1"
DEFAULT_PREFERENCE = "-1" 

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"
	
# 4.14
PV = "4.14+git+${SRCPV}"
SRCREV_pn-${PN} = "bebc6082da0a9f5d47a1ea2edc099bf671058bd4"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=git;branch=master \
        file://defconfig \
	file://0001-net-stmmac-snps-dwmac-mdio-MDIOs-are-automatically-r.patch \
	file://0002-net-stmmac-dwmac-sun8i-Handle-integrated-external-MD.patch \
	file://0003-net-stmmac-sun8i-Restore-the-compatibles.patch \
	file://0004-arm-dts-sunxi-h3-h5-Restore-EMAC-changes.patch \
	file://0005-ARM-dts-sunxi-h3-h5-represent-the-mdio-switch-used-b.patch \
	file://0006-ARM-dts-sunxi-Restore-EMAC-changes-boards.patch \
	file://0007-ARM-dts-sun8i-h3-Add-senic-hub-dts.patch \
        "

S = "${WORKDIR}/git"
