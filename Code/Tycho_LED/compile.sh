#!/bin/bash

/usr/bin/javac -cp ./bin:/home/pi/Processing/lib/*:/home/pi/Processing/core/library/*:./lib/base/net/*:/home/pi/sketchbook/libraries/video/library/*:./lib/user/oscP5/*:./lib/user/Syphon/* -d ./bin ./src/tycho/opc/*.java ./src/tycho/sensor/*.java ./src/tycho/Config.java ./src/tycho/led/*.java ./src/tycho/Tycho_LED.java
