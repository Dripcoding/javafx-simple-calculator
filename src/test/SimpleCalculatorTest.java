import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.App;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class SimpleCalculatorTest {

  private Scene scene;
  private Parent root;

  @BeforeEach
  private void setUp() throws Exception {
    ApplicationTest.launch(App.class);
  }

  @Start
  private void start(Stage stage) throws IOException {
    stage.show();
  }

  @AfterEach
  private void tearDown(FxRobot robot) throws TimeoutException {
    FxToolkit.hideStage();
    robot.release(new KeyCode[]{});
    robot.release(new MouseButton[]{});
  }

  @Test
  void shouldContainButtonWithText(FxRobot robot) {
    FxAssert.verifyThat("#primaryButton", LabeledMatchers.hasText("Switch to Secondary View"));

    FxAssert.verifyThat("#primaryButton", (Button button) -> {
      String text = button.getText();
      return text.equals("Switch to Secondary View");
    });
  }
}
