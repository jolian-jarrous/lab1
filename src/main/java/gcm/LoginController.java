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

    @FXML private TextField usernameField;     // the username text box
    @FXML private PasswordField passwordField;  // the password box
    @FXML private Label messageLabel;           // where we show the error message

    // The list of valid users, loaded from the input file.
    private ArrayList<User> users = new ArrayList<>();

    // initialize() runs automatically, once, right after the FXML is loaded.
    @FXML
    public void initialize() {
        loadUsers();
    }

    // Reads Users.txt and keeps ONLY the valid users (reuses the Lab 1 User class).
    private void loadUsers() {
        Scanner scanner = new Scanner(getClass().getResourceAsStream("/gcm/Users.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            if (parts.length < 2) continue;      // no password on the line -> skip

            try {
                users.add(new User(parts[0], parts[1]));   // added only if valid
            } catch (IllegalArgumentException e) {
                // invalid user -> we simply skip it (we keep only valid users)
            }
        }
        scanner.close();
    }

    // Runs when the user clicks the Login button (linked via onAction in the FXML).
    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // Look for a valid user whose username AND password both match.
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                openWelcomeScreen(username);   // success -> go to Welcome screen
                return;
            }
        }

        // No match -> show an error ON THE SCREEN (not a pop-up), per requirement 2.3.
        messageLabel.setText("Wrong username or password");
    }

    // Swaps the current login screen for the Welcome screen (loads a new FXML).
    private void openWelcomeScreen(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gcm/welcome.fxml"));
            Parent root = loader.load();

            // Give the welcome controller the name to greet.
            WelcomeController controller = loader.getController();
            controller.setWelcomeMessage("Welcome, " + username + "!");

            // Reuse the SAME window and just change its scene.
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            messageLabel.setText("Could not open the welcome screen");
        }
    }
}
