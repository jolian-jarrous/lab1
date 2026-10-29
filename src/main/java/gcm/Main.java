package gcm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// The Main class starts the JavaFX program.
// "extends Application" makes this a JavaFX app; JavaFX calls start() for us.
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load the login screen that we designed in login.fxml.
        Parent root = FXMLLoader.load(getClass().getResource("/gcm/login.fxml"));

        stage.setTitle("GCM Login");       // the window title
        stage.setScene(new Scene(root));   // put the login screen into the window
        stage.show();                      // show the window
        // Note: clicking the window's X closes it and ends the program
        // automatically (default JavaFX behaviour) -> requirement 2.4.
    }

    public static void main(String[] args) {
        launch(args);   // starts JavaFX, which then calls start() above
    }
}
