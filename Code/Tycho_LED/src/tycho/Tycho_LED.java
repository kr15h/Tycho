package tycho;

import netP5.NetAddress;
import oscP5.OscMessage;
import oscP5.OscP5;
import processing.core.PApplet;
import tycho.led.LEDPhotons;
import tycho.led.LEDs;
import tycho.sensor.ColorSensor;
import tycho.sensor.DummyColorSensor;
import tycho.sensor.CameraColorSensor;

@SuppressWarnings("serial")
public class Tycho_LED extends PApplet {

	private OscP5 osc;
	private NetAddress syphonSketch;

	// Color Sensor
	ColorSensor colorSensor;

	// LEDs
	LEDs leds;
	LEDPhotons photons;

	static final int FRAMES_PER_EMIT = 240;
	static final int TIME_BETWEEN_MSG = 100;

	int SIZE_W = 100;
	int SIZE_H = 6;

	boolean oscSent = false;

	public void settings(){
		//ARGS_LOCATION = "0,0";
		size(SIZE_W, SIZE_H, P2D);
	}

	public void setup() {
		//size(SIZE_W, SIZE_H);
		colorMode(HSB, 360, 100, 100);
		blendMode(ADD);

		PApplet pap = this;

		// Color Sensor
		colorSensor = new CameraColorSensor(pap);
		//colorSensor = new DummyColorSensor(pap);

		// LEDs
		leds = new LEDs(pap);
		photons = new LEDPhotons(pap);

		osc = new OscP5(this, 6666);
		syphonSketch = new NetAddress("192.168.2.1", 7777);

		background(0);
	}

	long lastMsg = 0;

	public void draw() {
		//frame.setLocation(9999, 9999);

		getSurface().hideCursor();
		getSurface().setLocation(0, 0);
		//getSurface().setVisible(false);

		background(0);

		// Color Sensor
		int col = colorSensor.readColor();
		showColorDebug(col);

		// LEDs
		if (frameCount % FRAMES_PER_EMIT == 0) {
			photons.emit(col);
		}

		photons.draw();

		if (photons.report()) {
			int reportColor = photons.reportColor();
			// println(reportColor);
			// println(hue(reportColor), saturation(reportColor),
			// brightness(reportColor));

			// show report indicator
			pushStyle();
			noStroke();
			fill(0, 100, 100);
			rect(0, 3, width, width);
			popStyle();

			if (System.currentTimeMillis() - lastMsg > TIME_BETWEEN_MSG) {
				// send OSC report
				OscMessage msg = new OscMessage("/contact");
				msg.add(reportColor);
				msg.add(hue(reportColor));
				msg.add(saturation(reportColor));
				msg.add(brightness(reportColor));

				if(oscSent == false){
					osc.send(msg, syphonSketch);
					oscSent = true;
				}
				lastMsg = System.currentTimeMillis();
			}
		}else{
			oscSent = false;
		}

		//showFrameRate();

		// Clear
		pushStyle();
                noStroke();
                fill(0);
                rect(0, 0, 800, 800);
                popStyle();

		//clear();
	}

	public void showColorDebug(int col) {
		pushStyle();
		noStroke();
		fill(col);

		// color indicator
		rect(0, 0, width * .1f, width * .1f);
		popStyle();
	}

	public void showFrameRate() {
		if (frameCount % 30 == 0) {
			frame.setTitle(nf(frameRate, 2, 2));
		}
	}

	public void keyPressed() {

	}

	public static void main(String _args[]) {
		PApplet.main(new String[] {
				// "--present",
				tycho.Tycho_LED.class.getName() });
	}

}
