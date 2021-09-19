package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController {

  @FXML private TextField resultTextField;
  @FXML private Button number1;
  @FXML private Button number2;
  @FXML private Button number3;
  @FXML private Button number4;
  @FXML private Button number5;
  @FXML private Button number6;
  @FXML private Button number7;
  @FXML private Button number8;
  @FXML private Button number9;
  @FXML private Button number0;
  @FXML private Button decimalButton;
  @FXML private Button clearButton;
  @FXML private Button plusButton;
  @FXML private Button minusButton;
  @FXML private Button timesButton;
  @FXML private Button divideButton;
  @FXML private Button equalsButton;

  public void handleClickNumber(ActionEvent event) {
    Button target = ((Button) event.getSource());
    String result = resultTextField.getText().concat(target.getText());
    resultTextField.setText(result);
  }
}
