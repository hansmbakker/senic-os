SUMMARY = "A test sound that is played when locating Sonos speakers"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/PD;md5=b3597d12946881e13cb3b548d1173851"

RDEPENDS_${PN} += "\
  lighttpd \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://ping.mp3"

do_install_append() {
  install -d ${D}/www/pages/resources
  install -m 0644 ${WORKDIR}/ping.mp3 ${D}/www/pages/resources/ping.mp3
}

FILES_${PN} = "\
  /www/pages/resources \
  /www/pages/resources/ping.mp3 \
"
