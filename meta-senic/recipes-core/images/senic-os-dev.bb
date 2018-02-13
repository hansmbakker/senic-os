require senic-os.bb

IMAGE_FEATURES = "debug-tweaks"

IMAGE_INSTALL += " \	
	htop \
	usbutils \
	vim \
	tree \
	sudo \
	git \
	curl \
	bash \
	bash-completion \
	python3-debugger \
	iperf3 \ 
	stress \
	rsync \
	screen \
	lmsensors-sensors \
	lmsensors-libsensors \
	lmsensors-sensorsconfconvert \
	lmsensors-sensord \
	lmsensors-pwmconfig \
	lmsensors-sensorsdetect \
	lmsensors-fancontrol \
	binutils \
	tcpdump \
"
