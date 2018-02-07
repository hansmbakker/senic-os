# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Logger for multiprocessing applications"
HOMEPAGE = "https://github.com/jruere/multiprocessing-logging"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
# NOTE: Original package / source metadata indicates license is: LGPL-3.0+
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "LGPLv3 & LGPL-3.0+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=94a3f3bdf61243b5e5cf569fbfbbea52"

SRC_URI = "git://github.com/jruere/multiprocessing-logging;protocol=https"
SRC_URI_append = " file://remove_tests.patch"

# Modify these as desired
PV = "0.2.6+git${SRCPV}"
SRCREV = "2f385adbd19ca809eedf0bbfd51a6886c61caf70"

S = "${WORKDIR}/git"

inherit setuptools3
