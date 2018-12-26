package de.kohl.philipp;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class FXMLController {

	private PCRemote remote = new PCRemote();

	@FXML
	private Slider sliderKp;

	@FXML
	private Slider sliderTv;

	@FXML
	private Slider sliderTn;

	public FXMLController() {

	}

	@FXML
	private void initialize() {

	}

	@FXML
	private void connectToBrick(ActionEvent actionEvent) {
		try {
			remote.connectToFirstBrick();
		} catch (IOException exception) {
			showError(exception);
		}
	}

	private void showError(Exception e) {
		System.out.println(e);
	}

	@FXML
	private void sendCommands(ActionEvent actionEvent) {
		double kp = sliderKp.getValue();
		double tv = sliderTv.getValue();
		double tn = sliderTn.getValue();

		String command = String.format("%f;%f;%f", kp, tv, tn);
		try {
			remote.sendCommand(command);
		} catch (IOException exception) {
			showError(exception);
		}
	}

}
