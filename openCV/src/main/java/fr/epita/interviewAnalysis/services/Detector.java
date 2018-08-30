package fr.epita.interviewAnalysis.services;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public abstract class Detector {

	protected CascadeClassifier DETECTOR;

	protected Mat FRAME;
	protected Mat GRAYFRAME;

	protected MatOfRect DETECTIONS;
	
	protected int COUNT;

	ConfigurationService conf = ConfigurationService.getInstance();

	/**
	 *  initialize the detector
	 *  any initial custom settings can be applied here
	 */
	protected abstract void init();

	/**
	 * constructor
	 */
	public Detector() {
		this.init();
	}

	/**
	 * constructor with the frame as a parameter
	 * @param frame
	 */
	public Detector(Mat frame) {
		setFrame(frame);
		this.init();
	}

	/**
	 * sets the frame and computes the gray frame
	 * @param frame
	 */
	public void setFrame(Mat frame) {
		this.FRAME = new Mat();
		this.GRAYFRAME = new Mat();
		
		this.FRAME = frame;
		
		// convert the frame in gray scale
		Imgproc.cvtColor(frame, GRAYFRAME, Imgproc.COLOR_BGR2GRAY);
		// equalize the frame histogram to improve the result
		Imgproc.equalizeHist(GRAYFRAME, GRAYFRAME);
	}
	
	public Mat getFrame() {
		return this.FRAME;
	}

	/**
	 * @return the detected objects
	 */
	public abstract MatOfRect detect();

	/**
	 * @return number of detected objects
	 */
	public int countDetectedObjects() {
		return this.COUNT;
	}
	
	/**
	 * draw the detected objects on the original frame
	 */
	public abstract void draw();
	
	/**
	 *  crops the frame
	 * @param objectIndex
	 * @return the coped matrix
	 */
	public Mat cropObject(int objectIndex) {
		Rect[] DetectionsArray = this.DETECTIONS.toArray();
		return this.FRAME.submat(DetectionsArray[objectIndex]);
	}

}
