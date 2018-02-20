SUMMARY = "Logs the uptime command output to a file"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = "file://uptime-log.service"

S = "${WORKDIR}"

SYSTEMD_SERVICE_${PN} = "uptime-log.service"

do_install() {
  install -d ${D}${systemd_system_unitdir}/
  install -m 0755 ${WORKDIR}/uptime-log.service ${D}${systemd_system_unitdir}/
}

FILES_${PN} += "${systemd_system_unitdir}/"

REQUIRED_DISTRO_FEATURES = "systemd"
