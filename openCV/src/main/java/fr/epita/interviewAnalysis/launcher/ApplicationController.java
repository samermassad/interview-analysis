package fr.epita.interviewAnalysis.launcher;

import java.io.File;

import org.opencv.core.Mat;

import fr.epita.interviewAnalysis.development.DevelopmentVariables;
import fr.epita.interviewAnalysis.services.VideoUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
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
	
	@FXML
	private Slider slider_dp;
	@FXML
	private Slider slider_minDist;
	@FXML
	private Slider slider_param1;
	@FXML
	private Slider slider_param2;
	@FXML
	private Slider slider_minRad;
	@FXML
	private Slider slider_maxRad;
	@FXML
	private Label lbl_dp;
	@FXML
	private Label lbl_minDist;
	@FXML
	private Label lbl_param1;
	@FXML
	private Label lbl_param2;
	@FXML
	private Label lbl_minRad;
	@FXML
	private Label lbl_maxRad;

	/**
	 * Init the controller, at start time
	 */
	protected void init() {
		// set a fixed width for the frame
		//outputFrame.setFitWidth(600);
		// preserve image ratio
		outputFrame.setPreserveRatio(true);
		
		// Setting default values for sliders
		this.slider_dp.setValue(DevelopmentVariables.getDp());
		this.slider_minDist.setValue(DevelopmentVariables.getMinDist());
		this.slider_param1.setValue(DevelopmentVariables.getParam1());
		this.slider_param2.setValue(DevelopmentVariables.getParam2());
		this.slider_minRad.setValue(DevelopmentVariables.getMinRad());
		this.slider_maxRad.setValue(DevelopmentVariables.getMaxRad());
		
		updateLabels();
	}

	private void updateLabels() {
		lbl_dp.setText("dp: " + Double.toString(DevelopmentVariables.getDp())); 
		lbl_minDist.setText("minDist: " + Double.toString(DevelopmentVariables.getMinDist()));
		lbl_param1.setText("param1: " + Double.toString(DevelopmentVariables.getParam1()));
		lbl_param2.setText("param2: " + Double.toString(DevelopmentVariables.getParam2()));
		lbl_minRad.setText("minRad: " + Integer.toString(DevelopmentVariables.getMinRad()));
		lbl_maxRad.setText("maxRad: " + Integer.toString(DevelopmentVariables.getMaxRad()));
	}

	@FXML
	protected void open() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open video files");

		// TO BE DELETED
		fileChooser.setInitialDirectory(new File("D:\\Desktop\\Video Files\\"));

		File file = fileChooser.showOpenDialog(root.getScene().getWindow());

		if (file.isFile()) {
			
			filename.setText(file.getName());
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
						vUtils.processFrame(frame);
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
	
	@FXML
	protected void updateValues() {
		DevelopmentVariables.setDp(this.slider_dp.getValue());
		DevelopmentVariables.setMinDist(this.slider_minDist.getValue());
		DevelopmentVariables.setParam1(this.slider_param1.getValue());
		DevelopmentVariables.setParam2(this.slider_param2.getValue());
		DevelopmentVariables.setMinRad((int) this.slider_minRad.getValue());
		DevelopmentVariables.setMaxRad((int) this.slider_maxRad.getValue());
		
		updateLabels();
		
		System.out.println("New Values: dp = " + DevelopmentVariables.getDp() + 
				" minDist = " + DevelopmentVariables.getMinDist() +
				" param1 = " + DevelopmentVariables.getParam1() +
				" param2 = " + DevelopmentVariables.getParam2() +
				" minRad = " + DevelopmentVariables.getMinRad() +
				" maxRad = " + DevelopmentVariables.getMaxRad()
				);
	}
	
}
