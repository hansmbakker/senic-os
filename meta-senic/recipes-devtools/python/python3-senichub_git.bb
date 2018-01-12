DESCRIPTION = "The main application powering the Senic Hub"
HOMEPAGE = "https://developers.senic.com/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit setuptools3

PROVIDES += "python3-senic-hub"
RPROVIDES_${PN} += "python3-senic-hub"


S = "${WORKDIR}/git"
SRC_URI = "git://github.com/getsenic/senic-hub.git;"
SRCREV = "01fd5bd8725add5e73d3cc35a169f0dc837daefa"
PV = "git-${SRCPV}"

RDEPENDS_${PN} = "python3-nuimo python3-lightify"
