package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/** JavaFX App */
public class App extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    Scene scene = new Scene(loadFXML("primary"), 300, 395);

    Image stageIcon = new Image("calculator.png");
    stage.getIcons().add(stageIcon);
    stage.setTitle("Simple Calculator");

    stage.centerOnScreen();
    stage.show();
  }

  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static void main(String[] args) {
    launch();
  }
}
