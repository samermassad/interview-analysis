package fr.epita.interviewAnalysis.services;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.videoio.VideoCapture;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class VideoUtils {

	private VideoCapture capture;
	
	public boolean init(String path) {
		this.capture = new VideoCapture(path);
		if(!capture.isOpened()) {
			return false;
		}
		return true;
	}

	public boolean grab() {
		return this.capture.grab();
	}

	public Mat retreive() {
		Mat frame = new Mat();
		this.capture.retrieve(frame);
		return frame;
	}

	public void release() {
		this.capture.release();
	}
	
	public void processFrame(Mat frame) {
		FaceDetector faceDetector = new FaceDetector(frame);
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
	}
	
	
	public static Image mat2Image(Mat frame)
	{
		try
		{
			return SwingFXUtils.toFXImage(matToBufferedImage(frame), null);
		}
		catch (Exception e)
		{
			System.err.println("Cannot convert the Mat object: " + e);
			return null;
		}
	}
	
	private static BufferedImage matToBufferedImage(Mat original)
	{
		// init
		BufferedImage image = null;
		int width = original.width(), height = original.height(), channels = original.channels();
		byte[] sourcePixels = new byte[width * height * channels];
		original.get(0, 0, sourcePixels);
		
		if (original.channels() > 1)
		{
			image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		}
		else
		{
			image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		}
		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
		
		return image;
	}
}
