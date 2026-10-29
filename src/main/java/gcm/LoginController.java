package gcm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginController {

    // These are linked to the elements in login.fxml by their fx:id.
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private ArrayList<User> users = new ArrayList<>();

    // Runs automatically when the screen loads: read the file, keep only valid users.
    @FXML
    public void initialize() {
        Scanner scanner = new Scanner(getClass().getResourceAsStream("/gcm/Users.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            if (parts.length < 2) continue;
            try {
                users.add(new User(parts[0], parts[1]));
            } catch (IllegalArgumentException e) {
                // invalid user, skip it
            }
        }
        scanner.close();
    }

    // Runs when the login button is clicked.
    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                openWelcomeScreen(username);
                return;
            }
        }
        messageLabel.setText("user or password do not match");
    }

    // Replace the login screen with the welcome screen, in the same window.
    private void openWelcomeScreen(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gcm/welcome.fxml"));
            Parent root = loader.load();
            loader.<WelcomeController>getController().setWelcomeMessage("Welcome, " + username + "!");
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            messageLabel.setText("Could not open the welcome screen");
        }
    }
}
