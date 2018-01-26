Changelog
=========

0.1.8 - 2018-01-26
------------------

* Mender implementation with Hosted mender and signed images 
* Ethernet working
* OS support for the dynastream module [bluetooth]
* Updated to Rocko [BlueZ 5.46]
* Kernel 4.14
* Partial porting of the senic Hub stack


0.1.7 - 2017-11-22
------------------

* Improve nuimo discovery


0.1.6 - 2017-11-15
------------------

* Migrated to kernel 4.13
* Support for em9304 and nrf52 with USB connection
* Implements senic-hub version 0.2.241+af8069e
* Works only with version 0.2.5+ of the mobile app


0.1.5 - 2017-11-07
------------------

* Patch for a bug introduced by the build system
* No features or bugfixes for our software stack 


0.1.4 - 2017-10-17 
------------------

* Reset button handler is started by systemd
* add sentry support (#57)
* move application configuration to /etc/ (away from data partition) (#44)


0.1.3 - 2017-10-11
------------------

* Include lm-sensors for development build (#51)

* Remove obsolete filesystem expansion on startup

* Re-enable senic-button (after switch to systemd)


0.1.2 - 2017-10-10
------------------

* Keep mender and yocto version numbers in sync


0.1.1 - 2017-10-10
------------------

* Internal brown-bag release, working out yocto versioning :-)


0.1.0 - 2017-10-10
------------------

* First versioned release.

* Upgrades senic-hub to 0.2.200 which includes cornice_swagger
  https://github.com/getsenic/senic-hub/pull/331

