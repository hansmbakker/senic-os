inherit senic-base

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://ntp.conf"
SRC_URI += "file://ntpdate.default"
SRC_URI += "file://ntpdate-sync"

python do_compile() {
  render_template('ntp.conf')
  render_template('ntpdate.default')
}

do_install_append() {
    install -m 644 ${WORKDIR}/ntp.conf ${D}${sysconfdir}
    install -m 644 ${WORKDIR}/ntpdate.default ${D}${sysconfdir}/default/ntpdate
    install -m 0755 -d ${D}${sysconfdir}/NetworkManager/dispatcher.d/
    install -m 755 ${WORKDIR}/ntpdate-sync ${D}${sysconfdir}/NetworkManager/dispatcher.d/ntpdate-sync
}

FILES_${PN} += "\
${sysconfdir}/NetworkManager \
${sysconfdir}/NetworkManager/dispatcher.d \
${sysconfdir}/NetworkManager/dispatcher.d/ntpdate-sync \
"
