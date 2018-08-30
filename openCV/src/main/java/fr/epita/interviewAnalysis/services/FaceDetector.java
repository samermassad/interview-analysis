package fr.epita.interviewAnalysis.services;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

public class FaceDetector extends Detector {

	private int absoluteFaceSize;

	public FaceDetector() {
		super();
	}
	
	public FaceDetector(Mat frame) {
		super(frame);
	}
	
	protected void init() {
		this.absoluteFaceSize = 0;
		this.DETECTOR = new CascadeClassifier(this.conf.getConfigurationValue("frontalFaceDetector.path"));
	}

	// detects the faces
	// returns matrix of rectangles MatOfRect
	public MatOfRect detect() {
		// compute minimum face size (10% of the frame height, in our case)
		if (this.absoluteFaceSize == 0) {
			int height = this.GRAYFRAME.rows();
			if (Math.round(height * 0.1f) > 0) {
				this.absoluteFaceSize = Math.round(height * 0.1f);
			}
		}

		this.DETECTIONS = new MatOfRect();
		this.DETECTOR.detectMultiScale(this.FRAME, this.DETECTIONS, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE,
				new Size(this.absoluteFaceSize, this.absoluteFaceSize), new Size());
		
		this.COUNT = this.DETECTIONS.toArray().length;
		
		return this.DETECTIONS;
	}

	@Override
	public void draw() {
		Rect[] facesArray = this.DETECTIONS.toArray();
		for (int i = 0; i < facesArray.length; i++) {
			Imgproc.rectangle(this.FRAME, facesArray[i].tl(), facesArray[i].br(), new Scalar(255, 255, 255), 3);
		}
	}

}
