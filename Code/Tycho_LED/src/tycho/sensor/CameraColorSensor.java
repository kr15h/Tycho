package tycho.sensor;

import processing.core.PApplet;
import processing.core.PImage;
import processing.video.Capture;

public class CameraColorSensor extends ColorSensor {
	Capture camera;

	// RESOLUTION
	// TODO: make this all less magic
	private final int RES_W = 160;
	private final int RES_H = 120;
	private final int TARGET_PIXEL = 40;
	private final int FRAMERATE = 30;
	private final String CAMERA_NAME = "Logitech Camera";
//	private final String CAMERA_NAME = "Logitech Camera #2";

	public CameraColorSensor(PApplet pap) {
		super(pap);

		String[] cameras = Capture.list();

		if(cameras.length == 0){
			System.out.println("No camz.");
		//	exit();
		}else{
			System.out.println("There are cams.");
			for(int i = 0; i < cameras.length; i++){
     				 System.out.println(cameras[i]);
    			}
		}

		camera = new Capture(pap, RES_W, RES_H, cameras[0]);
		camera.start();
	}

	public int readColor() {
		if (camera.available()) {
			camera.read();

			camera.loadPixels();
			lastColor = camera.pixels[TARGET_PIXEL];
		}

		return lastColor;
	}
	
	public PImage readImage() {
		if (camera.available()) {
			camera.read();
		}
		return camera.get();
	}
}
