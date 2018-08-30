package fr.epita.interviewAnalysis.services;

import org.opencv.core.Mat;

public class BackgroundAnalyser {

	private Mat FRAME;

	ConfigurationService conf = ConfigurationService.getInstance();
	
	public BackgroundAnalyser() {
		
	}

	public BackgroundAnalyser(Mat frame) {
		this.FRAME = frame;
	}
	
	public void setFrame(Mat frame) {
		this.FRAME = frame;
	}
	
	
	
}
