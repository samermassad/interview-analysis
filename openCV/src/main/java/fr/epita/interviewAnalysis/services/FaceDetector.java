package fr.epita.interviewAnalysis.services;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetector {

	private final CascadeClassifier FACEDETECTOR;

	private Mat FRAME;

	ConfigurationService conf = ConfigurationService.getInstance();

	public FaceDetector() {
		FACEDETECTOR = new CascadeClassifier(conf.getConfigurationValue("frontalFaceDetector.path"));
	}

	public FaceDetector(Mat frame) {
		this.FRAME = frame;
		FACEDETECTOR = new CascadeClassifier(conf.getConfigurationValue("frontalFaceDetector.path"));
	}

	public void setFrame(Mat frame) {
		this.FRAME = frame;
	}

	// detects the faces
	// returns matrix of rectangles MatOfRect
	public MatOfRect detectFaces() {
		MatOfRect faceDetections = new MatOfRect();
		FACEDETECTOR.detectMultiScale(FRAME, faceDetections);
		return faceDetections;
	}

	// returns int: the number of faces
	public int countFaces() {
		return detectFaces().toArray().length;
	}

	// returns list of rectangles face positions
	public boolean getFacesPosition() {
		// Draw a bounding box around each face.
		//for (Rect rect : detectFaces().toArray()) {
		//	Imgproc.rectangle(FRAME, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
		//			new Scalar(0, 255, 0));
		//}
//
		//String filename = "D:/Desktop/monkey.png";
		//Imgcodecs.imwrite(filename, FRAME);
		return true;
	}

}
