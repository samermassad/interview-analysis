package fr.epita.interviewAnalysis.test;

import static org.opencv.imgcodecs.Imgcodecs.imread;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.Mat;

import fr.epita.interviewAnalysis.services.FaceDetector;

public class FaceDetectorTests {

	@BeforeClass
	public static void loadLibrary() {
		System.load("E:\\openCV\\opencv\\build\\java\\x64\\opencv_java341.dll");
	}
	
	@Test
	public void testCountFaces() {
		
		
		Mat image = imread("C:\\Users\\samer\\Interview Analysis\\openCVTest\\resources\\countFaces.jpg");
		FaceDetector faceDetector = new FaceDetector(image);
		Assert.assertEquals(3, faceDetector.countFaces());
		
		image = imread("C:\\Users\\samer\\Interview Analysis\\openCVTest\\resources\\countFaces5Persons.png");
		faceDetector.setFrame(image);
		Assert.assertEquals(5, faceDetector.countFaces());
		
	}
	
}
