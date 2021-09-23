import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.example.App;
import org.example.FxIds;
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
    verifyThat(FxIds.NUMBER_1_BUTTON, LabeledMatchers.hasText("1"));
    verifyThat(FxIds.NUMBER_1_BUTTON, NodeMatchers.isVisible());

    verifyThat(FxIds.NUMBER_2_BUTTON, LabeledMatchers.hasText("2"));
    verifyThat(FxIds.NUMBER_2_BUTTON, NodeMatchers.isVisible());

    verifyThat(FxIds.NUMBER_3_BUTTON, LabeledMatchers.hasText("3"));
    verifyThat(FxIds.NUMBER_3_BUTTON, NodeMatchers.isVisible());

    verifyThat(FxIds.NUMBER_4_BUTTON, LabeledMatchers.hasText("4"));
    verifyThat(FxIds.NUMBER_4_BUTTON, NodeMatchers.isVisible());

    verifyThat(FxIds.NUMBER_5_BUTTON, LabeledMatchers.hasText("5"));
    verifyThat(FxIds.NUMBER_5_BUTTON, NodeMatchers.isVisible());

    verifyThat(FxIds.NUMBER_6_BUTTON, LabeledMatchers.hasText("6"));
    verifyThat(FxIds.NUMBER_6_BUTTON, NodeMatchers.isVisible());

    verifyThat(FxIds.NUMBER_7_BUTTON, LabeledMatchers.hasText("7"));
    verifyThat(FxIds.NUMBER_7_BUTTON, NodeMatchers.isVisible());

    verifyThat(FxIds.NUMBER_8_BUTTON, LabeledMatchers.hasText("8"));
    verifyThat(FxIds.NUMBER_8_BUTTON, NodeMatchers.isVisible());

    verifyThat(FxIds.NUMBER_9_BUTTON, LabeledMatchers.hasText("9"));
    verifyThat(FxIds.NUMBER_9_BUTTON, NodeMatchers.isVisible());

    verifyThat(FxIds.NUMBER_0_BUTTON, LabeledMatchers.hasText("0"));
    verifyThat(FxIds.NUMBER_0_BUTTON, NodeMatchers.isVisible());
  }

  @Test
  void shouldContainBasicArithmeticButtons(FxRobot robot) {
    verifyThat(FxIds.PLUS_BUTTON, LabeledMatchers.hasText("+"));
    verifyThat(FxIds.PLUS_BUTTON, NodeMatchers.isVisible());

    verifyThat(FxIds.MINUS_BUTTON, LabeledMatchers.hasText("-"));
    verifyThat(FxIds.MINUS_BUTTON, NodeMatchers.isVisible());

    verifyThat(FxIds.TIMES_BUTTON, LabeledMatchers.hasText("x"));
    verifyThat(FxIds.TIMES_BUTTON, NodeMatchers.isVisible());

    verifyThat(FxIds.DIVIDE_BUTTON, LabeledMatchers.hasText("/"));
    verifyThat(FxIds.DIVIDE_BUTTON, NodeMatchers.isVisible());

    verifyThat(FxIds.DECIMAL_BUTTON, LabeledMatchers.hasText("."));
    verifyThat(FxIds.DECIMAL_BUTTON, NodeMatchers.isVisible());

    verifyThat(FxIds.CLEAR_BUTTON, LabeledMatchers.hasText("clear"));
    verifyThat(FxIds.CLEAR_BUTTON, NodeMatchers.isVisible());

    verifyThat(FxIds.EQUALS_BUTTON, LabeledMatchers.hasText("="));
    verifyThat(FxIds.EQUALS_BUTTON, NodeMatchers.isVisible());
  }


  @Test
  void shouldContainTextFieldToShowResults(FxRobot robot) {
    verifyThat(FxIds.RESULTS_TEXT_FIELD, NodeMatchers.isVisible());
    verifyThat(FxIds.RESULTS_TEXT_FIELD, (TextField textField) -> {
      assertNotNull(textField);
      return textField.getText().isEmpty();
    });
  }

  @Test
  void resultTextFieldShouldShowSelectedNumbers(FxRobot robot) {
    robot.clickOn(FxIds.NUMBER_1_BUTTON);
    robot.clickOn(FxIds.NUMBER_2_BUTTON);
    robot.clickOn(FxIds.NUMBER_3_BUTTON);
    robot.clickOn(FxIds.DECIMAL_BUTTON);
    robot.clickOn(FxIds.NUMBER_1_BUTTON);
    robot.clickOn(FxIds.NUMBER_2_BUTTON);
    robot.clickOn(FxIds.NUMBER_3_BUTTON);

    WaitForAsyncUtils.waitForFxEvents();

    verifyThat(FxIds.RESULTS_TEXT_FIELD, (TextField textField) -> {
      assertNotNull(textField);
      return textField.getText().equals("123.123");
    });
  }

  @Test
  void shouldHandleAdditionCorrectly(FxRobot robot) {
    robot.clickOn(FxIds.NUMBER_1_BUTTON);
    robot.clickOn(FxIds.PLUS_BUTTON);
    robot.clickOn(FxIds.NUMBER_3_BUTTON);
    robot.clickOn(FxIds.EQUALS_BUTTON);

    WaitForAsyncUtils.waitForFxEvents();

    verifyThat(FxIds.RESULTS_TEXT_FIELD, (TextField textField) -> {
      assertNotNull(textField);
      return textField.getText().equals("4.0");
    });
  }


  @Test
  void shouldHandleSubtractionCorrectly(FxRobot robot) {
    robot.clickOn(FxIds.NUMBER_1_BUTTON);
    robot.clickOn(FxIds.NUMBER_0_BUTTON);
    robot.clickOn(FxIds.NUMBER_0_BUTTON);
    robot.clickOn(FxIds.MINUS_BUTTON);
    robot.clickOn(FxIds.NUMBER_1_BUTTON);
    robot.clickOn(FxIds.EQUALS_BUTTON);

    WaitForAsyncUtils.waitForFxEvents();

    verifyThat(FxIds.RESULTS_TEXT_FIELD, (TextField textfield) -> {
      assertNotNull(textfield);
      return textfield.getText().equals("99.0");
    });
  }

  @Test
  void shouldHandleMultiplicationCorrectly(FxRobot robot) {
    robot.clickOn(FxIds.NUMBER_1_BUTTON);
    robot.clickOn(FxIds.NUMBER_0_BUTTON);
    robot.clickOn(FxIds.NUMBER_0_BUTTON);
    robot.clickOn(FxIds.TIMES_BUTTON);
    robot.clickOn(FxIds.NUMBER_1_BUTTON);
    robot.clickOn(FxIds.NUMBER_0_BUTTON);
    robot.clickOn(FxIds.NUMBER_0_BUTTON);
    robot.clickOn(FxIds.EQUALS_BUTTON);

    WaitForAsyncUtils.waitForFxEvents();

    verifyThat(FxIds.RESULTS_TEXT_FIELD, (TextField textfield) -> {
      assertNotNull(textfield);
      return textfield.getText().equals("10000.0");
    });
  }

  @Test
  void shouldHandleDivisionCorrectly(FxRobot robot) {
    robot.clickOn(FxIds.NUMBER_1_BUTTON);
    robot.clickOn(FxIds.NUMBER_0_BUTTON);
    robot.clickOn(FxIds.NUMBER_0_BUTTON);
    robot.clickOn(FxIds.DIVIDE_BUTTON);
    robot.clickOn(FxIds.NUMBER_1_BUTTON);
    robot.clickOn(FxIds.NUMBER_0_BUTTON);
    robot.clickOn(FxIds.EQUALS_BUTTON);

    WaitForAsyncUtils.waitForFxEvents();

    verifyThat(FxIds.RESULTS_TEXT_FIELD, (TextField textfield) -> {
      assertNotNull(textfield);
      return textfield.getText().equals("10.0");
    });
  }

  @Test
  void shouldHandleClearCorrectly(FxRobot robot) {
    robot.clickOn(FxIds.NUMBER_1_BUTTON);
    robot.clickOn(FxIds.NUMBER_0_BUTTON);
    robot.clickOn(FxIds.NUMBER_0_BUTTON);
    robot.clickOn(FxIds.PLUS_BUTTON);
    robot.clickOn(FxIds.NUMBER_1_BUTTON);
    robot.clickOn(FxIds.EQUALS_BUTTON);

    WaitForAsyncUtils.waitForFxEvents();

    verifyThat(FxIds.RESULTS_TEXT_FIELD, (TextField textfield) -> {
      assertNotNull(textfield);
      return textfield.getText().equals("101.0");
    });

    robot.clickOn(FxIds.CLEAR_BUTTON);

    WaitForAsyncUtils.waitForFxEvents();

    verifyThat(FxIds.RESULTS_TEXT_FIELD, (TextField textfield) -> {
      assertNotNull(textfield);
      return textfield.getText().equals("");
    });
  }

  @Test
  void shouldNotAllowMultipleOperatorsToBeChosenAtOnce(FxRobot robot) {
    robot.clickOn(FxIds.NUMBER_1_BUTTON);
    robot.clickOn(FxIds.NUMBER_2_BUTTON);
    robot.clickOn(FxIds.PLUS_BUTTON);
    robot.clickOn(FxIds.PLUS_BUTTON);

    WaitForAsyncUtils.waitForFxEvents();

    verifyThat(FxIds.RESULTS_TEXT_FIELD, (TextField textfield) -> {
      assertNotNull(textfield);
      return textfield.getText().contains("12 + ");
    });
  }

  @Test
  void shouldDisplayWhitespaceBeforeAndAfterOperator(FxRobot robot) {
    robot.clickOn(FxIds.NUMBER_1_BUTTON);
    robot.clickOn(FxIds.MINUS_BUTTON);
    robot.clickOn(FxIds.NUMBER_1_BUTTON);

    WaitForAsyncUtils.waitForFxEvents();

    verifyThat(FxIds.RESULTS_TEXT_FIELD, (TextField textfield) -> {
      assertNotNull(textfield);
      return textfield.getText().equals("1 - 1");
    });
  }
}
