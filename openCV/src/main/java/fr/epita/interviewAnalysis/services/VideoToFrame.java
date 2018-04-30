package fr.epita.interviewAnalysis.services;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import javafx.scene.image.Image;

public class VideoToFrame {

	private VideoCapture capture = new VideoCapture();

	public ArrayList<Mat> captureFrames(String videoLocation) {
		ArrayList<Mat> framesArray = new ArrayList<>();

		if (this.capture.open(videoLocation)) {
			Mat frame = new Mat();
			while (this.capture.read(frame)) {
				framesArray.add(frame);
				frame = new Mat();
			}

		} else {
			System.err.println("cannot open the video");
		}
		return framesArray;
	}

}
