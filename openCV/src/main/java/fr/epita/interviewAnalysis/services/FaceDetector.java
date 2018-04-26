package fr.epita.interviewAnalysis.services;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetector {

	private final CascadeClassifier FACEDETECTOR = new CascadeClassifier("E:\\openCV\\opencv\\sources\\data\\lbpcascades\\lbpcascade_frontalface.xml");
	
	private Mat FRAME;
	
	public FaceDetector() {
		
	}
	
	public FaceDetector(Mat frame) {
		this.FRAME = frame;
	}
	
	public void setFrame(Mat frame) {
		this.FRAME = frame;
	}
	
	public int countFaces() {
		// Detect faces in the image.
		// MatOfRect is a special container class for Rect.
		MatOfRect faceDetections = new MatOfRect();
		FACEDETECTOR.detectMultiScale(FRAME, faceDetections);
		return faceDetections.toArray().length;
	}
	
	public void detectFaces() {
		// Draw a bounding box around each face.
		//for (Rect rect : faceDetections.toArray()) {
		//	rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
		//	new Scalar(0, 255, 0));
		//}
	}

}
