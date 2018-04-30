package fr.epita.interviewAnalysis.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.Mat;

import fr.epita.interviewAnalysis.services.ConfigurationService;
import fr.epita.interviewAnalysis.services.VideoToFrame;

public class VideoToFrameTest {

	@BeforeClass
	public static void loadLibrary() {
		ConfigurationService conf = ConfigurationService.getInstance();
		String path = conf.getConfigurationValue("library.path");
		System.load(path);
	}
	
	@Test
	public void loadVideo() {
		VideoToFrame vtf = new VideoToFrame();
		ArrayList<Mat> frames = vtf.captureFrames("/videoTest.mp4");
		assertTrue(frames.size() > 0);
	}
	
}
