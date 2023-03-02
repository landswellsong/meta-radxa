# Meta-radxa

# Table of contents

1. [Introduction](#introduction)
2. [Dependencies](#dependencies)
3. [List of Radxa Boards supported](#list_of_boards_supported)
4. [Using the meta-radxa layer](#meta_radxa_usage)
    1. [Step 1:Fetching the Source](#source_fetch)
    2. [Step 2:Setting up the Environment](#setup)
    3. [Step 3:Bblayers.conf Setup](#bblayers.conf_setup)
    4. [Step 4:Local.conf Setup](#local.conf_setup)
    5. [Step 5:Building the Image](#build_image)
5. [Serial Console](#serial_console)
6. [Login Details](#login)
7. [Networking](#networking)
    1. [Wifi Connectivity](#wifi)
    2. [Bluetooth Connectivity](#bluetooth)    
8. [Release Info](#release_info)
9. [Change Log](#change_log)
10. [Contributing](#contributing)
11. [Reporting Bugs](#bugs)
12. [Maintainers](#maintainers)


## Introduction <a name="introduction"></a>

The OpenEmbedded/Yocto Project BSP layer for the Radxa machines

## Dependencies <a name="dependencies"></a>

The meta-radxa layer depends on:

	URI: git://git.yoctoproject.org/poky
	branch: dunfell
    
	URI: git://git.openembedded.org/meta-openembedded
	layers: meta-oe meta-networking meta-python meta-multimedia meta-gnome meta-xfce
	branch: dunfell

## List of Radxa Boards supported <a name="list_of_boards_supported"></a>

1) RockPi-4A
2) RockPi-4B
3) RockPi-4C
4) RockPi-E
5) RockPi-N10
6) RockPi-PX30
7) RockPi-S
8) Radxa-Zero

## Using the meta-radxa layer <a name="meta_radxa_usage"></a>

### Step 1: Fetching the source <a name="source_fetch"></a>

Fetch the source using the commands given below:

<pre><code>~ $ mkdir yocto
~ $ cd yocto
~/yocto $ git clone --depth 1 git://git.yoctoproject.org/poky -b dunfell
~/yocto $ cd poky
~/yocto/poky $ git clone --depth 1 git://git.openembedded.org/meta-openembedded.git -b dunfell
~/yocto/poky $ git clone --depth 1 https://github.com/radxa/meta-radxa.git -b dunfell
~/yocto.poky $ git clone --depth 1 https://github.com/YoeDistro/meta-python2.git -b dunfell
</code></pre>

### Step 2: Setting up the Environment <a name="setup"></a>

<pre><code>~/yocto/poky $ source oe-init-build-env
</code></pre>

#### Step 3: bblayers.conf Setup <a name="bblayers.conf_setup"></a>

* You can simply copy the bblayers.conf.sample present in meta-radxa/conf folder to the build/conf folder and rename it to bblayers.conf

<pre><code>~/yocto/poky/build $ cp ../meta-radxa/conf/bblayers.conf.sample conf/bblayers.conf
</code></pre>

<div align="center"><b>OR</b></div>

* Add the layers manually as given below to the bblayers.conf in the build/conf folder

<pre><code>  ${TOPDIR}/../meta \
  ${TOPDIR}/../meta-poky \
  ${TOPDIR}/../meta-yocto-bsp \
  ${TOPDIR}/../meta-openembedded/meta-oe \
  ${TOPDIR}/../meta-openembedded/meta-networking \
  ${TOPDIR}/../meta-openembedded/meta-python \
  ${TOPDIR}/../meta-openembedded/meta-multimedia \ 
  ${TOPDIR}/../meta-openembedded/meta-gnome \
  ${TOPDIR}/../meta-openembedded/meta-xfce \
  ${TOPDIR}/../meta-python2 \
  ${TOPDIR}/../meta-radxa \
</code></pre>

### Step 4: local.conf Setup <a name="local.conf_setup"></a>

* You can simply copy the local.conf.sample present in meta-radxa/conf folder to the build/conf folder and rename it to local.conf and uncomment the machine for which you want to build an image

<pre><code>~/yocto/poky/build $ cp ../meta-radxa/conf/local.conf.sample conf/local.conf
~/yocto/poky/build $ nano conf/local.conf
</code></pre>

<div align="center"><b>OR</b></div>

* Add the following lines in the build/conf/local.conf

```
MACHINE ?= "xxxx"
DISTRO_FEATURES:append = " pam systemd x11"
VIRTUAL-RUNTIME:init_manager = "systemd"
PACKAGECONFIG:append:pn-systemd = " resolved networkd"
DISTRO_FEATURES:remove = "wayland"
```

**Replace xxxx with the machine you want to build the image for. All supported machines can be found in meta-radxa/conf/machine.**

### Step 5: Building the Image <a name="build_image"></a>

* If you wish to build a minimal image use the command given below:
```
~/yocto/poky/build $ bitbake -k radxa-minimal-image
```
<div align="center"><b>OR</b></div>

* If you wish to build a console image use the command given below:

```
~/yocto/poky/build $ bitbake -k radxa-console-image
```
<div align="center"><b>OR</b></div>

* If you wish to build a desktop image use the command given below:

```
~/yocto/poky/build $ bitbake -k radxa-desktop-image
```

**At the end of a successful build, you should have a gpt.img image in build/tmp/deploy/images/MACHINE/ folder. The gpt.img can be directly flashed on the sd-card**

## Serial Console <a name="serial_console"></a>

The Serial Console for RockPi-4, RockPi-E and RockPi-N10 is enabled on UART-2. The Serial Console for RockPi-S is enabled on UART-0.

**Helpful Links:**

+ GPIO Pinout for RockPi-4 (https://wiki.radxa.com/Rockpi4/hardware/gpio)
+ GPIO Pinout for RockPi-E (https://wiki.radxa.com/RockpiE/hardware/rockpiE#gpio)
+ GPIO Pinout for RockPi-N10 (https://wiki.radxa.com/RockpiN10/hardware/rockpiN10#gpio)
+ GPIO Pinout for RockPi-S (https://wiki.radxa.com/RockpiS/hardware/gpio)
+ RockPi-4 Serial Console Setup (https://wiki.radxa.com/Rockpi4/dev/serial-console)
+ RockPi-E Serial Console Setup (https://wiki.radxa.com/RockpiE/dev/serial-console)
+ RockPi-N10 Serial Console Setup (https://wiki.radxa.com/RockpiN10/dev/serial-console)
+ RockPi-S Serial Console Setup (https://wiki.radxa.com/RockpiS/dev/serial-console)
+ Radxa-Zero Serial Console Setup (https://wiki.radxa.com/Zero/dev/serial-console)

## Login Details <a name="login"></a>

```
Username: root
Password: rock
```

## Networking <a name="networking"></a>

**Network Devices available:**

+ Wifi
+ Ethernet
+ Bluetooth

### Wifi Connectivity <a name="wifi"></a>

+ Using Commandline Based GUI(nmtui) [Available on console and desktop images]

nmtui is a curses based GUI. You can start it by running the following command:

```
nmtui
```

+ Using Commandline Utility(nmcli) [Available on console and desktop images]

nmcli is a command-line tool for controlling NetworkManager and reporting network status.

**List available devices**

```
nmcli dev
```

**Turn on Wifi**

```
nmcli r wifi on
```

**Scanning different devices**

```
nmcli dev wifi
```

**Connect to WiFi Hotspot**

```
nmcli dev wifi connect "SSID" password "PASSWORD"
```

***Note:You need to replace “SSID” and “Password” with your actual WiFi’s SSID and password.***

### Bluetooth Connectivity <a name="bluetooth"></a>

+ Bluetooth on RockPi-4B/RockPi-4C

**Activating bluetooth:**

```
hciconfig hci0 up
```

**Check Bluetooth device:**

```
 $ hciconfig
 hci0:   Type: Primary  Bus: UART
         BD Address: 43:45:C5:00:1F:AC  ACL MTU: 1021:8  SCO MTU: 64:1
         UP RUNNING 
         RX bytes:876 acl:0 sco:0 events:62 errors:0
         TX bytes:4755 acl:0 sco:0 commands:62 errors:0
```

+ Bluetooth on RockPi-E

**Manual setup for bluetooth:**

Bluetooth is activated by default on the RockPi-E

**Check Bluetooth device:**

```
 $ hciconfig
 hci0:   Type: Primary  Bus: USB
         BD Address: 74:EE:2A:55:23:F7  ACL MTU: 1021:8  SCO MTU: 255:12
         UP RUNNING 
         RX bytes:40357 acl:34 sco:0 events:814 errors:0
         TX bytes:216782 acl:379 sco:0 commands:101 errors:0
```

+ Bluetooth on RockPi-N10

**Activating bluetooth:**

```
hciconfig hci0 up
```

**Check Bluetooth device:**

```
 $ hciconfig
 hci0:   Type: Primary  Bus: UART
         BD Address: 22:22:70:B2:10:6F  ACL MTU: 1021:8  SCO MTU: 255:12
         UP RUNNING 
         RX bytes:1399 acl:0 sco:0 events:45 errors:0
         TX bytes:3458 acl:0 sco:0 commands:45 errors:0
```

+ Bluetooth on RockPi-S

**Activating bluetooth:**

```
hciconfig hci0 up
```

**Check Bluetooth device:**

```
 $ hciconfig
 hci0:   Type: Primary  Bus: UART
         BD Address: 22:22:70:B2:10:6F  ACL MTU: 1021:8  SCO MTU: 255:12
         UP RUNNING 
         RX bytes:1399 acl:0 sco:0 events:45 errors:0
         TX bytes:3458 acl:0 sco:0 commands:45 errors:0
```

## Release Info <a name="release_info"></a>

1. RockPi-4

+ Kernel version: 4.4.154-109-b04eccb4588e333bdaf3ba7e6e4186d2ebe53770
+ U-Boot version: 2017.09-04d66f4b45a47531b5ff6cdbddcdc2cc35fa7aea

2. RockPi-E

+ Kernel version: 4.4.194-12-615ae743115011bbe1cd1edc5c9118bf95527f54
+ U-Boot version: 2019.10-7b93f1b8bce4106266d4a38dde96fd8080faccea

3. RockPi-N10

+ Kernel version: 4.4.167-c36a21e2be755919e8b406069206e67126b7e712
+ U-Boot version: 2017.09-7c49a7da79caf2f68bc1074dfa21864d7f23b9e1

4. RockPi-PX30

+ Kernel version: 4.4.189-dc47906e88166ae315f0472743de4d80f2bea786
+ U-Boot version: 2017.09-cfc37de87bc064b2d6d384566e24c5e4245f113a

5. RockPi-S

+ Kernel version: 4.4.143-55-6b7accbc999b6caa8ef603b9d904c99694d0bf41
+ U-Boot version: 2017.09-233a23e3ed0b3e5250253ee455c3c5df2080f99c

## Change Log <a name="change_log"></a>

+ Added board support for RockPi-N10
+ Added board support for RockPi-PX30
+ Added board support for RockPi-4A
+ Added board support for RockPi-4C
+ RockPi-4 Kernel updated to 4.4.154-109-b04eccb4588e333bdaf3ba7e6e4186d2ebe53770
+ RockPi-4 U-Boot branch updated from stable-4.4-rockpi4 to rk3399-pie-gms-express-baseline
+ RockPi-4 U-Boot updated to 2017.09-04d66f4b45a47531b5ff6cdbddcdc2cc35fa7aea
+ RockPi-4 boards support SPI + NVME booting
+ RockPi-4b dtb changed from rockpi-4b-linux.dtb to rk3399-rock-pi-4b.dtb
+ RockPi-S Kernel updated to 4.4.143-55-6b7accbc999b6caa8ef603b9d904c99694d0bf41 
+ RockPi-S U-Boot updated to 2017.09-c93a0fc70993c12e605b1312577252ca9cdec6c8
+ RockPi-S and RockPi-E gpt images have now been updated to use the 2 partition instead of the tradition 5 partition
+ Use ttySx as debuger console instead of ttyFIQ0 for RockPi-S and RockPi-E
+ Major Reorganization in U-Boot recipes. New structure will make it easier for adding new boards
+ Added recipe for making desktop images
+ Added recipe for setting up BT firmware and driver at boot for Rockpi-4, RockPi-E, RockPi-N10 and RockPi-S
+ Added recipe for kernel-headers, file-system resize at boot, mraa and create-ap
+ Added board support for Radxa-Zero

## Contributing <a name="contributing"></a>

Please use github for pull requests: https://github.com/nishantpoorswani/meta-radxa/pulls

## Reporting bugs <a name="bugs"></a>

The github issue tracker (https://github.com/nishantpoorswani/meta-radxa/issues) is being used to keep track of bugs.

## Maintainers <a name="maintainers"></a>

* Nishant Poorswani <nishantpoorswani@gmail.com>
