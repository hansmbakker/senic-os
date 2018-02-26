DESCRIPTION = "The main application powering the Senic Hub"
HOMEPAGE = "https://developers.senic.com/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit setuptools3

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/getsenic/senic-hub.git;"
SRCREV = "2fe388bba506add4630353f3add75c65221cf26c"
PV = "git-${SRCPV}"

RDEPENDS_${PN} = "python3-nuimo python3-lightify"
