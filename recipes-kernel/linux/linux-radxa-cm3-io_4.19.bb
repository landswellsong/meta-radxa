DESCRIPTION = "Linux kernel for Radxa-CM3-IO"

inherit kernel
inherit python3native
require recipes-kernel/linux/linux-yocto.inc

# We need mkimage for the overlays
DEPENDS += "openssl-native u-boot-mkimage-radxa-native"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
	git://github.com/radxa/kernel.git;branch=stable-4.19-rock3; \
	file://0001-arm64-dts-rockchip-radxa-cm3-io-enable-serial-consol.patch \
	file://0004-extern-yyloc.patch \
"

SRCREV = "f0dc4a74f925ca01952c3031c1024bf9a581d65e"
LINUX_VERSION = "4.19.193"

# Override local version in order to use the one generated by linux build system
# And not "yocto-standard"
LINUX_VERSION_EXTENSION = ""
PR = "r1"
PV = "${LINUX_VERSION}+git${SRCREV}"

# Include only supported boards for now
COMPATIBLE_MACHINE = "(rk3036|rk3066|rk3288|rk3328|rk3399|rk3308|rk3399pro|rk3566)"
deltask kernel_configme

# Make sure we use /usr/bin/env ${PYTHON_PN} for scripts
do_patch:append() {
	for s in `grep -rIl python ${S}/scripts`; do
		sed -i -e '1s|^#!.*python[23]*|#!/usr/bin/env ${PYTHON_PN}|' $s
	done
}

do_compile:append() {
	oe_runmake dtbs
}

do_deploy:append() {
	install -d ${DEPLOYDIR}/overlays
	install -m 644 ${WORKDIR}/linux-radxa_cm3_io_rk3566-standard-build/arch/arm64/boot/dts/rockchip/overlay/* ${DEPLOYDIR}/overlays
}
