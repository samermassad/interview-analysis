package fr.epita.interviewAnalysis.services;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class EyeDetector extends Detector {

	public EyeDetector() {
		super();
	}
	
	public EyeDetector(Mat frame) {
		super(frame);
	}
	
	@Override
	public void init() {
		this.DETECTOR = new CascadeClassifier(this.conf.getConfigurationValue("eyesDetector.path"));
	}

	@Override
	public MatOfRect detect() {
		// DEFAULT VALUE, WORKS FINE
		double scaleFactor = 1.1;

		// BEST VALUES BETWEEN 3 AND 6, MUST TEST AND DETERMINE WHICH IS THE BEST
		int minNeighbour = 3;

		// I THINK NO NEED TO BE CHANGED
		int flags = 0;

		this.DETECTIONS = new MatOfRect();
		
		this.DETECTOR.detectMultiScale(this.FRAME, this.DETECTIONS, scaleFactor, minNeighbour, flags, new Size(30, 30),
				new Size());
		
		this.COUNT = this.DETECTIONS.toArray().length;
		
		return this.DETECTIONS;

	}

	@Override
	public void draw() {
		Rect[] eyesArray = this.DETECTIONS.toArray();
		for (int i = 0; i < eyesArray.length; i++) {
			Imgproc.rectangle(this.FRAME, eyesArray[i].tl(), eyesArray[i].br(), new Scalar(255, 255, 255), 3);
		}
	}

}
