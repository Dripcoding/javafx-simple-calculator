import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.example.App;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)
class SimpleCalculatorTest extends ApplicationTest{

  private Stage primaryStage;

  @BeforeEach
  private void setUp() throws Exception {
    ApplicationTest.launch(App.class);
  }

  @Start
  public void start(Stage stage) {
    primaryStage = stage;
  }

  @AfterEach
  private void tearDown(FxRobot robot) throws TimeoutException {
    FxToolkit.hideStage();
    robot.release(new KeyCode[]{});
    robot.release(new MouseButton[]{});
  }

  @Test
  void stageShouldHaveTitle(FxRobot robot) {
    assertEquals("Simple Calculator", primaryStage.getTitle());
  }

  @Test
  void stageShouldHaveIcon() {
    Image image = primaryStage.getIcons().get(0);

    assertNotNull(image);
  }

  @Test
  void shouldContainButtonsForDigitsZeroThroughNine(FxRobot robot) {
    verifyThat("#number1", LabeledMatchers.hasText("1"));
    verifyThat("#number1", NodeMatchers.isVisible());

    verifyThat("#number2", LabeledMatchers.hasText("2"));
    verifyThat("#number2", NodeMatchers.isVisible());

    verifyThat("#number3", LabeledMatchers.hasText("3"));
    verifyThat("#number3", NodeMatchers.isVisible());

    verifyThat("#number4", LabeledMatchers.hasText("4"));
    verifyThat("#number4", NodeMatchers.isVisible());

    verifyThat("#number5", LabeledMatchers.hasText("5"));
    verifyThat("#number5", NodeMatchers.isVisible());

    verifyThat("#number6", LabeledMatchers.hasText("6"));
    verifyThat("#number6", NodeMatchers.isVisible());

    verifyThat("#number7", LabeledMatchers.hasText("7"));
    verifyThat("#number7", NodeMatchers.isVisible());

    verifyThat("#number8", LabeledMatchers.hasText("8"));
    verifyThat("#number8", NodeMatchers.isVisible());

    verifyThat("#number9", LabeledMatchers.hasText("9"));
    verifyThat("#number9", NodeMatchers.isVisible());

    verifyThat("#number0", LabeledMatchers.hasText("0"));
    verifyThat("#number0", NodeMatchers.isVisible());
  }

  @Test
  void shouldContainBasicArithmeticButtons(FxRobot robot) {
    verifyThat("#plusButton", LabeledMatchers.hasText("+"));
    verifyThat("#plusButton", NodeMatchers.isVisible());

    verifyThat("#minusButton", LabeledMatchers.hasText("-"));
    verifyThat("#minusButton", NodeMatchers.isVisible());

    verifyThat("#timesButton", LabeledMatchers.hasText("x"));
    verifyThat("#timesButton", NodeMatchers.isVisible());

    verifyThat("#divideButton", LabeledMatchers.hasText("/"));
    verifyThat("#divideButton", NodeMatchers.isVisible());

    verifyThat("#decimalButton", LabeledMatchers.hasText("."));
    verifyThat("#decimalButton", NodeMatchers.isVisible());

    verifyThat("#clearButton", LabeledMatchers.hasText("clear"));
    verifyThat("#clearButton", NodeMatchers.isVisible());

    verifyThat("#equalsButton", LabeledMatchers.hasText("="));
    verifyThat("#equalsButton", NodeMatchers.isVisible());
  }


  @Test
  void shouldContainTextFieldToShowResults(FxRobot robot) {
    verifyThat("#resultTextField", NodeMatchers.isVisible());
    verifyThat("#resultTextField", (TextField textField) -> {
      assertNotNull(textField);
      return textField.getText().isEmpty();
    });
  }

  @Test
  void resultTextFieldShouldShowSelectedNumbers(FxRobot robot) {
    robot.clickOn("#number1");
    robot.clickOn("#number2");
    robot.clickOn("#number3");
    robot.clickOn("#decimalButton");
    robot.clickOn("#number1");
    robot.clickOn("#number2");
    robot.clickOn("#number3");

    WaitForAsyncUtils.waitForFxEvents();

    verifyThat("#resultTextField", (TextField textField) -> {
      assertNotNull(textField);
      return textField.getText().equals("123.123");
    });
  }

  @Test
  void shouldHandleAdditionCorrectly(FxRobot robot) {
    robot.clickOn("#number1");
    robot.clickOn("#plusButton");
    robot.clickOn("#number3");
    robot.clickOn("#equalsButton");

    WaitForAsyncUtils.waitForFxEvents();

    verifyThat("#resultTextField", (TextField textField) -> {
      assertNotNull(textField);
      return textField.getText().equals("4.0");
    });
  }


  @Test
  void shouldHandleSubtractionCorrectly(FxRobot robot) {
    robot.clickOn("#number1");
    robot.clickOn("#number0");
    robot.clickOn("#number0");
    robot.clickOn("#minusButton");
    robot.clickOn("#number1");
    robot.clickOn("#equalsButton");

    WaitForAsyncUtils.waitForFxEvents();

    verifyThat("#resultTextField", (TextField textfield) -> {
      assertNotNull(textfield);
      return textfield.getText().equals("99.0");
    });
  }

  @Test
  void shouldHandleMultiplicationCorrectly(FxRobot robot) {
    robot.clickOn("#number1");
    robot.clickOn("#number0");
    robot.clickOn("#number0");
    robot.clickOn("#timesButton");
    robot.clickOn("#number1");
    robot.clickOn("#number0");
    robot.clickOn("#number0");
    robot.clickOn("#equalsButton");

    WaitForAsyncUtils.waitForFxEvents();

    verifyThat("#resultTextField", (TextField textfield) -> {
      assertNotNull(textfield);
      return textfield.getText().equals("10000.0");
    });
  }

  @Test
  void shouldHandleClearCorrectly(FxRobot robot) {
    robot.clickOn("#number1");
    robot.clickOn("#number0");
    robot.clickOn("#number0");
    robot.clickOn("#plusButton");
    robot.clickOn("#number1");
    robot.clickOn("#equalsButton");

    WaitForAsyncUtils.waitForFxEvents();

    verifyThat("#resultTextField", (TextField textfield) -> {
      assertNotNull(textfield);
      return textfield.getText().equals("101.0");
    });

    robot.clickOn("#clearButton");

    WaitForAsyncUtils.waitForFxEvents();

    verifyThat("#resultTextField", (TextField textfield) -> {
      assertNotNull(textfield);
      return textfield.getText().equals("");
    });
  }
}
