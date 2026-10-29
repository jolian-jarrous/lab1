package gcm;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WelcomeController {

    @FXML private Label welcomeLabel;

    // Called by the login screen to set the greeting after a successful login.
    public void setWelcomeMessage(String message) {
        welcomeLabel.setText(message);
    }
}
