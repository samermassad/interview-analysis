package fr.epita.interviewAnalysis.test;

import static org.opencv.imgcodecs.Imgcodecs.imread;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.objdetect.CascadeClassifier;

// Detects number of faces in an image.
class DetectFaceDemo {

	public void run() {
		
		// Create a face detector from the cascade file in the resources
		// directory.
		CascadeClassifier faceDetector = new CascadeClassifier(Constances.FACEXMLPATH);
		
		Mat image = imread("C:\\Users\\samer\\Interview Analysis\\openCVTest\\resources\\countFaces.jpg");
		// Detect faces in the image.
		// MatOfRect is a special container class for Rect.
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);
		System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
		
		image = imread("C:\\Users\\samer\\Interview Analysis\\openCVTest\\resources\\countFaces5Persons.png");
		// Detect faces in the image.
		// MatOfRect is a special container class for Rect.
		faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);
		System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
		
		
		// Draw a bounding box around each face.
		//for (Rect rect : faceDetections.toArray()) {
		//	rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
		//			new Scalar(0, 255, 0));
		//}
	}
}

public class countFaces {
	public static void main(String[] args) {
		
		// Load the native library.
		System.load(Constances.LIBRARYPATH);
		new DetectFaceDemo().run();
	}
}