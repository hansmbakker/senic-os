FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

MENDER_SERVER_URL = "https://hosted.mender.io"

SRC_URI += "file://mender-device-identity"

do_install_append() {
  install -m 0755 ${WORKDIR}/mender-device-identity ${D}/usr/share/mender/identity/mender-device-identity
}
