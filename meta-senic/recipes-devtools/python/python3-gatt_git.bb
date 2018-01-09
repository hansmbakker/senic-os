SUMMARY = "Bluetooth GATT SDK for Python"
HOMEPAGE = "https://github.com/getsenic/gatt-python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit setuptools3

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/getsenic/gatt-python.git;"
SRCREV = "1061676812cbc66d0641e992ff7748f0ab03d61e"
PV = "git-${SRCPV}"
