package gcm;

// The User class describes ONE user: an email (username) and a password.
// It also knows the rules for what counts as a valid user.
// This is the SAME class from Lab 1, reused here (now inside the gcm package).
// "implements Comparable<User>" means users can be compared to each other.
public class User implements Comparable<User> {

    private String username;
    private String password;

    // The symbols we allow inside a password.
    private static final String SYMBOLS = "!@#$%^&*()-_=+~`";

    // Constructor: builds a User only if the email and password are valid,
    // otherwise it throws an error and the object is NOT created.
    public User(String username, String password) {
        checkUsername(username);
        checkPassword(password);
        this.username = username;
        this.password = password;
    }

    private void checkUsername(String u) {
        String emailPattern = "[A-Za-z0-9._%+-]+@[A-Za-z0-9][A-Za-z0-9.-]*\\.[A-Za-z]{2,}";
        if (!u.matches(emailPattern)) {
            throw new IllegalArgumentException("Please enter a valid Email as username");
        }
        if (u.length() > 50) {
            throw new IllegalArgumentException("Username is too long, try something shorter");
        }
    }

    private void checkPassword(String p) {
        if (p.length() < 8) {
            throw new IllegalArgumentException("Your password is too short, add more characters");
        }
        if (p.length() > 12) {
            throw new IllegalArgumentException("Your password is too long, try a shorter one");
        }
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (SYMBOLS.indexOf(c) >= 0) {
                hasSymbol = true;
            } else {
                throw new IllegalArgumentException("Please enter a valid password");
            }
        }
        if (!hasLetter || !hasDigit || !hasSymbol) {
            throw new IllegalArgumentException("Please enter a valid password");
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int compareTo(User other) {
        return this.username.compareTo(other.username);
    }

    public String toString() {
        return username + " " + password;
    }
}
