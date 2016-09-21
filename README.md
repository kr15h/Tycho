Tycho Setup Guide
=================

Hardware
--------

// TODO : instructions for hanging and connecting hardware



# Software

## Raspberry Pi

Installing Fadecandy is easy. First, clone the repository.

```
git clone https://github.com/scanlime/fadecandy.git
```

Then compile it. It should take less than 10 minutes.

```
cd fadecandy/server
make submodules
make
```

Make the binary accessible by the shell.

```
sudo mv fcserver /usr/local/bin
```

Use crontab to start it at boot. First, open crontab.

```
crontab -e
```

If it asks your preferences regarding text editors, type 2 for nano and hit Enter. Add the following line to the file.

```
@reboot /usr/local/bin/fcserver
```

Press Ctrl+x and y and Enter to save and exit. Now on each reboot the server will be launched automaniacally. Open the web browser on the Raspberry Pi and test the server by using the following URL.

```
http://localhost:7890
```

It should display a basic website with the title Fadecandy Server. It shows the connected devices and what not.


## Mac OS X

1. connect all hardware first, as described above
2. run `OPC/fcserver-osx`
  - test by going to http://localhost:7890/
  - you should see **Fadecandy LED Controller** under Connected Devices
    - If you see **No devices are connected!**, check connection between computer and Fadecandy
  - use the **Test Patterns** dropdown menu to test that LEDs are working properly
    - If Fadecandy is connected, but LEDs do not light, check that LEDs have power and are connected to Fadecandy
3. run `Tycho_LED`
  - LEDs should start flowing
  - // TODO: potential issues:
    - camera not connected
    - camera named incorrectly
4. open MadMapper using `Resources/MadMapper/Tycho.mad`
  - perform remapping as needed
5. run `Tycho_Projection`
  - ripples should now appear when LEDs reach surface, and video footage should change periodically
  - // TODO: potential issues:
    - ...

### Software Pieces

- `OPC/fcserver-osx` - manages communication between computer and Fadecandy/LEDs
- `Tycho_LED` - Processing program, runs camera and LEDs, tells `Tycho_Projection` when to create ripples
- `Tycho_Projection` - Processing program, generates coloured ripples and manages fading between presets in MadMapper
- `MadMapper` - projection mapping software, plays video footage and overlays ripples sent from `Tycho_Projection`
