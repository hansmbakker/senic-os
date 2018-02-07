DESCRIPTION = "The main application powering the Senic Hub"
HOMEPAGE = "https://developers.senic.com/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit setuptools3

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/getsenic/senic-hub.git;"
SRCREV = "7aee3b80ccc1ebd26056d7f1bba5f9259b2b2bf4"
PV = "git-${SRCPV}"

RDEPENDS_${PN} = "python3-nuimo python3-lightify"
