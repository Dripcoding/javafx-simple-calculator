package org.example;

import java.util.ArrayList;
import java.util.List;
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

  private List<String> primitivesList = new ArrayList<>();
  private double currentValue = 0d;

  public void handleClickNumber(ActionEvent event) {
    Button target = ((Button) event.getSource());
    String result = resultTextField.getText().concat(target.getText());

    primitivesList.add(target.getText());
    resultTextField.setText(result);
  }

  public void handleClickArithmeticOperation(ActionEvent event) {
    Button target = ((Button) event.getSource());
    primitivesList.add(target.getText());

    resultTextField.setText(resultTextField.getText().concat(target.getText()));
  }

  public void handleClickEquals(ActionEvent event) {
    double result = calculate(primitivesList);
    resultTextField.setText(String.valueOf(result));
  }

  private double calculate(List<String> primitivesList) {
    double operand1 = Double.parseDouble(primitivesList.get(0));
    double operand2 = Double.parseDouble(primitivesList.get(2));
    String operator = primitivesList.get(1);
    double result = 0;

    if (operator.equals("+")) {
      result = operand1 + operand2;
    }

    currentValue = result;

    resultTextField.setText(String.valueOf(currentValue));

    return currentValue;
  }

  private boolean isOperatorChosen() {
    return primitivesList.contains("+")
        || primitivesList.contains("-")
        || primitivesList.contains("*")
        || primitivesList.contains("/");
  }
}
