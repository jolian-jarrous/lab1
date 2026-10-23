import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UsersApp {

    public static void main(String[] args) {

        // A list to store the users that turn out to be valid.
        ArrayList<User> users = new ArrayList<>();

        // When we run the program we feed Users.txt into it.
        Scanner scanner = new Scanner(System.in);

        // Keep reading while there are still lines left.
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                continue;   // skip empty lines
            }

            // Split the line into pieces wherever there are spaces.
            String[] parts = line.split("\\s+");

            // A valid line must have at least a username AND a password.
            if (parts.length < 2) {
                System.err.println(line + "  ->  Please enter a valid password");
                continue;
            }

            String username = parts[0];
            String password = parts[1];

            // Try to build a User. If the constructor throws an error,
            // we catch it and print why the line was rejected.
            try {
                User user = new User(username, password);
                users.add(user);   // only reached if the user was valid
            } catch (IllegalArgumentException e) {
                // ERRORS print to the console.
                System.err.println(line + "  ->  " + e.getMessage());
            }
        }

        scanner.close();

        // Sort the valid users by username.
        Collections.sort(users);

        //write the valid users into a file called out.txt ---
        try (PrintWriter writer = new PrintWriter(new FileWriter("out.txt"))) {
            for (User user : users) {
                writer.println(user);   // writes "username password" into the file
            }
        } catch (IOException e) {
            // If the file could not be written, report it on the console.
            System.err.println("Could not write to out.txt: " + e.getMessage());
        }
    }
}