package fr.epita.interviewAnalysis.services;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import fr.epita.interviewAnalysis.development.DevelopmentVariables;

public class IrisDetector extends Detector {

	private double dp, minDist, param1, param2;
	private int minRad, maxRad;
	
	public IrisDetector() {
		super();
	}
	
	public IrisDetector(Mat frame) {
		super(frame);
	}
	
	@Override
	protected void init() {
		// TODO: find and set the final values
		updateValues();
	}

	private void updateValues() {
		this.dp = DevelopmentVariables.getDp();
		this.minDist = DevelopmentVariables.getMinDist();
		this.param1 = DevelopmentVariables.getParam1();
		this.param2 = DevelopmentVariables.getParam2();
		this.minRad = DevelopmentVariables.getMinRad();
		this.maxRad = DevelopmentVariables.getMaxRad();
	}

	@Override
	public MatOfRect detect() {
		
		// apply Gaussian Blur
		Mat eye = this.GRAYFRAME;
		Imgproc.GaussianBlur(eye, eye, new Size(9, 9), 1);

		this.DETECTIONS = new MatOfRect();
		Imgproc.HoughCircles(eye, this.DETECTIONS, Imgproc.CV_HOUGH_GRADIENT, this.dp, this.minDist, this.param1, this.param2,
				this.minRad, this.maxRad);
		
		return this.DETECTIONS;
	}

	@Override
	public void draw() {
		
		double x;
		double y;
		int r;

		int numCircles = this.DETECTIONS.cols();
		
		for (int c = 0; c < numCircles; c++) {

			double[] data = this.DETECTIONS.get(0, c);
			x = data[0];
			y = data[1];
			r = (int) data[2];
			
			Point center = new Point(x, y);
			// circle center
			Imgproc.circle(this.FRAME, center, 3, new Scalar(0, 255, 0), -1);
			// circle outline
			Imgproc.circle(this.FRAME, center, r, new Scalar(0, 0, 255), 1);
		}
	}

}
