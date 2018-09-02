package fr.epita.interviewAnalysis.launcher;

import java.io.File;

import org.opencv.core.Mat;

import fr.epita.interviewAnalysis.services.VideoUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class ApplicationController {
	
	@FXML
	private VBox root;

	@FXML
	private MenuItem open;

	@FXML
	private Label filename;

	@FXML
	private ImageView outputFrame;

	/**
	 * Init the controller, at start time
	 */
	protected void init() {
		// set a fixed width for the frame
		outputFrame.setFitWidth(600);
		// preserve image ratio
		outputFrame.setPreserveRatio(true);
	}

	@FXML
	protected void open() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open video files");

		// TO BE DELETED
		fileChooser.setInitialDirectory(new File("D:\\Desktop\\HTC Sensation XL\\Video"));

		File file = fileChooser.showOpenDialog(root.getScene().getWindow());

		if (file.isFile()) {
			filename.setText(file.getAbsolutePath());
			processAndDisplayVideo(file);

		} else {
			// log the error
			System.err.println("Failed to open the camera connection...");
		}

	}

	private void processAndDisplayVideo(final File file) {
		
		Runnable frameGrabber = new Runnable() {
			public void run() {
				VideoUtils vUtils = new VideoUtils();
				Mat frame = new Mat();
				if(vUtils.init(file.getAbsolutePath())) {
					while(vUtils.grab()) {
						frame = vUtils.retreive();
//						frame = processFrame();
						outputFrame.setImage(VideoUtils.mat2Image(frame));
					}
					vUtils.release();
				} else {
					// TODO
					// video can't be loaded
					System.out.println("Problem");
				}
			}
		};
		
		new Thread(frameGrabber).start();
	}

}
