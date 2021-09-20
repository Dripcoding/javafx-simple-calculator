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

  private String operand1 = "";
  private String operand2 = "";
  private String operator = "";

  public void handleClickNumber(ActionEvent event) {
    Button target = ((Button) event.getSource());
    String result = resultTextField.getText().concat(target.getText());

    if (isOperatorChosen()) {
      operand2 += target.getText();
    } else {
      operand1 += target.getText();
    }

    resultTextField.setText(result);
  }

  public void handleClickArithmeticOperation(ActionEvent event) {
    Button target = ((Button) event.getSource());
    operator = target.getText();

    resultTextField.setText(resultTextField.getText().concat(target.getText()));
  }

  public void handleClickEquals() {
    double result = calculate();
    resultTextField.setText(String.valueOf(result));
  }

  public void handleClickClear() {
    resultTextField.clear();
    operand1 = "";
    operand2 = "";
    operator = "";
  }

  private double calculate() {
    double currentValue;
    double operand1Value = Double.parseDouble(operand1);
    double operand2Value = Double.parseDouble(operand2);
    double result = 0;

    if (operator.equals("+")) {
      result = operand1Value + operand2Value;
    }

    currentValue = result;

    resultTextField.setText(String.valueOf(currentValue));

    return currentValue;
  }

  private boolean isOperatorChosen() {
    return operator.contains("+")
        || operator.contains("-")
        || operator.contains("*")
        || operator.contains("/");
  }
}
