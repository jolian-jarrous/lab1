package gcm;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

// The controller behind welcome.fxml. Its only job is to show the greeting.
public class WelcomeController {

    @FXML private Label welcomeLabel;   // linked to the Label in welcome.fxml

    // Called by the LoginController to set the greeting text after a successful login.
    public void setWelcomeMessage(String message) {
        welcomeLabel.setText(message);
    }
}
