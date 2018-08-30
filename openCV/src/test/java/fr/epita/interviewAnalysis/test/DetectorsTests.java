package fr.epita.interviewAnalysis.test;

import static org.opencv.imgcodecs.Imgcodecs.imread;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

import fr.epita.interviewAnalysis.services.ConfigurationService;
import fr.epita.interviewAnalysis.services.EyeDetector;
import fr.epita.interviewAnalysis.services.FaceDetector;
import fr.epita.interviewAnalysis.services.IrisDetector;

public class DetectorsTests {

	@BeforeClass
	public static void loadLibrary() {
		ConfigurationService conf = ConfigurationService.getInstance();
		String path = conf.getConfigurationValue("library.path");
		System.load(path);
	}

	@Test
	public void testDetectFaces() {
		Mat image = imread("src/test/resources/countFaces.jpg");
		FaceDetector faceDetector = new FaceDetector(image);
		EyeDetector eyeDetector = new EyeDetector();
		IrisDetector irisDetector = new IrisDetector();
		
		Rect[] faces = faceDetector.detect().toArray();
		faceDetector.draw();
		
		for(int i = 0; i<faces.length; i++) {
			Mat face = faceDetector.cropObject(i);
			eyeDetector.setFrame(face);
			
			Rect[] eyes = eyeDetector.detect().toArray();
			eyeDetector.draw();
			
			for(int j = 0; j<eyes.length; j++) {
				Mat eye = eyeDetector.cropObject(j);
				irisDetector.setFrame(eye);
				
				irisDetector.detect();
				irisDetector.draw();
			}
		}
		
		Imgcodecs.imwrite("src/test/resources/outputCountFaces.jpg", faceDetector.getFrame());
		
		Assert.assertEquals(3, faceDetector.countDetectedObjects());

		image = imread("src/test/resources/countFaces5Persons.png");
		faceDetector.setFrame(image);
		faces = faceDetector.detect().toArray();
		faceDetector.draw();
		
		for(int i = 0; i<faces.length; i++) {
			Mat face = faceDetector.cropObject(i);
			eyeDetector.setFrame(face);
			
			Rect[] eyes = eyeDetector.detect().toArray();
			eyeDetector.draw();
			
			for(int j = 0; j<eyes.length; j++) {
				Mat eye = eyeDetector.cropObject(j);
				irisDetector.setFrame(eye);
				
				irisDetector.detect();
				irisDetector.draw();
			}
		}
		Imgcodecs.imwrite("src/test/resources/outputcountFaces5Persons.jpg", faceDetector.getFrame());
		
		Assert.assertEquals(5, faceDetector.countDetectedObjects());
	}
}
