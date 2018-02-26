DESCRIPTION = "The main application powering the Senic Hub"
HOMEPAGE = "https://developers.senic.com/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit setuptools3

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/getsenic/senic-hub.git;"
SRCREV = "35603b07186ade3a83bc95ce9abec0e3c3c0827c"
PV = "git-${SRCPV}"

RDEPENDS_${PN} = "python3-nuimo python3-lightify"
