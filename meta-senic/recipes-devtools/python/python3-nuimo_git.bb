DESCRIPTION = "Nuimo SDK for Python on Linux"
HOMEPAGE = "https://github.com/getsenic/nuimo-linux-python"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


inherit setuptools3

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/getsenic/nuimo-linux-python.git;"
SRCREV = "1918e6e51ad6569eb134904e891122479fafa2d6"
PV = "git-${SRCPV}"

RDEPENDS_${PN} = "python3-gatt"
