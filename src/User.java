public class User implements Comparable<User> {

    private String username;
    private String password;

    // The symbols we allow inside a password.
    private static final String SYMBOLS = "!@#$%^&*()-_=+~`";

    // This is the CONSTRUCTOR. It runs when we write "new User(...)".
    // It checks the email and the password first. If something is wrong,
    // it throws an error and the object is NOT created.
    public User(String username, String password) {
        checkUsername(username);
        checkPassword(password);
        this.username = username;
        this.password = password;
    }

    // Checks the email rules. If a rule is broken, throw an error message.
    private void checkUsername(String u) {
        String emailPattern = "[A-Za-z0-9._%+-]+@[A-Za-z0-9][A-Za-z0-9.-]*\\.[A-Za-z]{2,}";

        if (!u.matches(emailPattern)) {
            throw new IllegalArgumentException("Please enter a valid Email as username");
        }
        if (u.length() > 50) {
            throw new IllegalArgumentException("Username is too long, try something shorter");
        }
    }

    // Checks the password rules.
    private void checkPassword(String p) {
        if (p.length() < 8) {
            throw new IllegalArgumentException("Your password is too short, add more characters");
        }
        if (p.length() > 12) {
            throw new IllegalArgumentException("Your password is too long, try a shorter one");
        }

        // We need at least one letter, one digit and one symbol.
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;

        // Look at every character one by one.
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

        // If any of the three is still missing, the password is invalid.
        if (!hasLetter || !hasDigit || !hasSymbol) {
            throw new IllegalArgumentException("Please enter a valid password");
        }
    }

    // Lets other classes READ the values but not change them.
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