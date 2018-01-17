SUMMARY = "Configure the senic hub"
PV="${DISTRO_VERSION}"
DESCRIPTION = "\
The main configuration recipe for the senic hub. \
It's where it all comes together...\
"
DEPENDS_${PN} = "\
  python-supervisor \
  python-daemonize \
  python3 \
  python3-asn1crypto \
  python3-cffi \
  python3-chardet \
  python3-click \
  python3-colander \
  python3-contextlib2 \
  python3-cornice \
  python3-cryptography \
  python3-cryptoyaml \
  python3-dbus \
  python3-enum-compat \
  python3-fasteners \
  python3-gatt \
  python3-hupper \
  python3-idna \
  python3-iso8601 \
  python3-lightify \
  python3-monotonic \
  python3-multiprocessing \
  python3-netdisco \
  python3-netifaces \
  python3-nuimo \
  python3-pastedeploy \
  python3-pbkdf2 \
  python3-phue \
  python3-pip \
  python3-plaster \
  python3-pydoc \
  python3-pygobject \
  python3-pyramid \
  python3-pyramid-tm \
  python3-python-networkmanager \
  python3-pytz \
  python3-pyyaml \
  python3-raven \
  python3-repoze-lru \
  python3-requests \
  python3-senic-hub \
  python3-simplejson \
  python3-six \
  python3-soco \
  python3-transaction \
  python3-translationstring \
  python3-urllib3 \
  python3-venusian \
  python3-waitress \
  python3-webob \
  python3-websocket-client \
  python3-xmltodict \
  python3-zeroconf \
  python3-zope.deprecation \
  python3-zope.interface \
  bluez5 \
"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI = "file://supervisor_senic_hub.conf"
SRC_URI += "file://supervisor_bluenet.conf"
SRC_URI += "file://supervisor_netwatch.conf"
SRC_URI += "file://supervisor_nuimo_app.conf"
SRC_URI += "file://supervisor_device_discovery.conf"
SRC_URI += "file://production.ini"
SRC_URI += "file://locales.sh"
SRC_URI += "file://LICENSE.txt"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE.txt;md5=62d64e0a0688cba2e9ede69d1f702e1c"

inherit senic-base
inherit logging
inherit useradd
inherit deploy

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-U -d ${SNC_BACKEND_DEPLOY_LOCATION} ${SNC_RUNTIME_USER}"

do_install() {
    # create and populate the deployment location
    install -m 0755 -o ${SNC_BUILD_USER} -g ${SNC_RUNTIME_USER} -d ${D}${SNC_BACKEND_DEPLOY_LOCATION}

    # configure supervisor processes
    install -m 0755 -d ${D}${sysconfdir}/supervisor/conf.d/
    install -m 0755 ${WORKDIR}/supervisor_senic_hub.conf ${D}${sysconfdir}/supervisor/conf.d/senic_hub.conf
    install -m 0755 ${WORKDIR}/supervisor_bluenet.conf ${D}${sysconfdir}/supervisor/conf.d/bluenet.conf
    install -m 0755 ${WORKDIR}/supervisor_netwatch.conf ${D}${sysconfdir}/supervisor/conf.d/netwatch.conf
    install -m 0755 ${WORKDIR}/supervisor_nuimo_app.conf ${D}${sysconfdir}/supervisor/conf.d/nuimo_app.conf
    install -m 0755 ${WORKDIR}/supervisor_device_discovery.conf ${D}${sysconfdir}/supervisor/conf.d/device_discovery.conf

    # app configuration
    install -m 0755 ${WORKDIR}/production.ini ${D}${sysconfdir}/senic_hub.ini

    # global system configuration
    install -m 0755 -d ${D}${sysconfdir}/profile.d/
    install -m 0644 ${WORKDIR}/locales.sh ${D}${sysconfdir}/profile.d/locales.sh
}

do_deploy() {
    # populate the data partition
    install -m 0755 -d ${DEPLOYDIR}/hub-data/senic-hub
    install -m 0755 --d ${DEPLOYDIR}/hub-data/senic-hub/logs
}

addtask do_deploy after do_compile before do_build

FILES_${PN} = "\
    ${SNC_BACKEND_DEPLOY_LOCATION} \
    ${sysconfdir}/senic_hub.ini \
    ${sysconfdir}/supervisor/conf.d/senic_hub.conf \
    ${sysconfdir}/supervisor/conf.d/bluenet.conf \
    ${sysconfdir}/supervisor/conf.d/netwatch.conf \
    ${sysconfdir}/supervisor/conf.d/nuimo_app.conf \
    ${sysconfdir}/supervisor/conf.d/device_discovery.conf \
    ${sysconfdir}/profile.d/locales.sh \
"
