SYSTEMD_SERVICE_${PN} = "NetworkManager.service NetworkManager-dispatcher.service NetworkManager-wait-online.service "

RDEPENDS_${PN} += "bash"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append= " file://99-eth0"

do_install_append() {
    rm -r ${D}${sysconfdir}/NetworkManager/system-connections
    ln -sf /data/senic-hub/etc/NetworkManager/system-connections ${D}${sysconfdir}/NetworkManager/

    # Disable eth0 interface
    install -m 0755 ${WORKDIR}/99-eth0 ${D}${sysconfdir}/NetworkManager/dispatcher.d/pre-up.d/99-eth0
}
