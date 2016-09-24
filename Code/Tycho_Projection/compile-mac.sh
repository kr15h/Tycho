#!/bin/bash

#/usr/bin/javac -cp ./bin:/home/pi/Processing/lib/*:/home/pi/Processing/core/library/*:./lib/base/net/*:/home/pi/sketchbook/libraries/video/library/*:./lib/user/oscP5/*:./lib/user/Syphon/* -d ./bin ./src/tycho/opc/*.java ./src/tycho/sensor/*.java ./src/tycho/Config.java ./src/tycho/led/*.java ./src/tycho/Tycho_LED.java

#!/bin/bash

/usr/bin/javac -cp ./bin:./lib/base/core/*:./lib/base/net/*:./lib/base/video/*:./lib/user/oscP5/*:./lib/user/Syphon/* -d ./bin ./src/tycho/effects/*.java ./src/tycho/effects/scene/*.java ./src/tycho/madmapper/*.java ./src/tycho/particle/*.java ./src/tycho/Config.java ./src/tycho/Tycho_Projection.java
