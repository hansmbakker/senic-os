SUMMARY = "A library to work with OSRAM lightify."
HOMEPAGE = "https://github.com/aneumeier/python-lightify"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit setuptools3

PROVIDES += "python3-python-networkmanager"       
RPROVIDES_${PN} += "python3-python-networkmanager"


S = "${WORKDIR}/git"
SRC_URI = "git://github.com/getsenic/python-networkmanager.git;"
SRCREV = "aea55e82cfd888e12bb4cd7b7c4fffba1a6c09ba"
PV = "git-${SRCPV}"
