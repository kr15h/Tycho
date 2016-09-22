#!/bin/bash

/usr/bin/javac -cp ./bin:./lib/base/core/*:./lib/base/net/*:./lib/base/video/*:./lib/user/oscP5/*:./lib/user/Syphon/* -d ./bin ./src/tycho/opc/*.java ./src/tycho/sensor/*.java ./src/tycho/Config.java ./src/tycho/led/*.java ./src/tycho/Tycho_LED.java
