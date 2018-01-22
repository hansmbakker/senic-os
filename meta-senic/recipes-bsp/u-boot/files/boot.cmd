setenv fdtfile boot/sun8i-h3-senic-hub.dtb
setenv bootargs 'console=${console} root=${mender_kernel_root} rootwait panic=10 ${extra}'
setenv mmcboot 'load ${mender_uboot_root} ${fdt_addr_r} ${fdtfile}; load ${mender_uboot_root} ${kernel_addr_r} boot/zImage; bootz ${kernel_addr_r} - ${fdt_addr_r}'
setenv bootcmd 'run mender_setup; run mmcboot'
boot
