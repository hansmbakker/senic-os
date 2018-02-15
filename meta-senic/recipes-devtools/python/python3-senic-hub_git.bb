DESCRIPTION = "The main application powering the Senic Hub"
HOMEPAGE = "https://developers.senic.com/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit setuptools3

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/getsenic/senic-hub.git;"
SRCREV = "3187482eb38fd02f2e85b1d4df71c8f82d578b1c"
PV = "git-${SRCPV}"

RDEPENDS_${PN} = "python3-nuimo python3-lightify"
