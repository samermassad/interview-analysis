package fr.epita.interviewAnalysis.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.opencv.imgcodecs.Imgcodecs.imread;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.Mat;

import fr.epita.interviewAnalysis.services.VideoUtils;
import nu.pattern.OpenCV;

public class VideoUtilsTests {

	@BeforeClass
	public static void loadLibrary() {
		// load the native OpenCV library
		OpenCV.loadShared();
	}

	@Test
	public void loadVideo() {
		VideoUtils vUtils = new VideoUtils();
		Mat frame = new Mat();
		String video = getClass().getResource( "/videoTest.mp4" ).getFile();
		assertTrue(vUtils.init(new File(video).getAbsolutePath()));
		vUtils.grab();
		frame = vUtils.retreive();
		assertNotNull(frame);
		vUtils.release();
	}
	
	@Test
	public void mat2Image() {
		Mat image = imread("src/test/resources/countFaces.jpg");
		assertNotNull(VideoUtils.mat2Image(image));
	}

}
