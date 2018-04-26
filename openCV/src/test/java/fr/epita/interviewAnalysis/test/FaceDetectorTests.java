package fr.epita.interviewAnalysis.test;

import static org.opencv.imgcodecs.Imgcodecs.imread;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.Mat;

import fr.epita.interviewAnalysis.services.ConfigurationService;
import fr.epita.interviewAnalysis.services.FaceDetector;

public class FaceDetectorTests {

	@BeforeClass
	public static void loadLibrary() {
		ConfigurationService conf = ConfigurationService.getInstance();
		String path = conf.getConfigurationValue("library.path");
		System.load(path);
	}
	
	@Test
	public void testCountFaces() {
		
		
		Mat image = imread("src/test/resources/countFaces.jpg");
		FaceDetector faceDetector = new FaceDetector(image);
		Assert.assertEquals(3, faceDetector.countFaces());
		
		image = imread("src/test/resources/countFaces5Persons.png");
		faceDetector.setFrame(image);
		Assert.assertEquals(5, faceDetector.countFaces());
		
	}
	
}
