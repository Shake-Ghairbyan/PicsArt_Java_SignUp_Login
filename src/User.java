import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ExceptionsForRegistration.*;

public class User {
    private String fullName;
    private String username;
    private String email;
    private String passwordHash;

    public User(String s) throws FullNameException, UsernameException, EmailException {
        String[] split = s.split(",");
        setFullName(split[0]);
        setUsername(split[1]);
        setEmail(split[2]);
        passwordHash = split[3];
    }

    public User() {

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) throws FullNameException {
        if (fullName.matches("[A-Z][a-z]*[ ][A-Z][a-z]*")) {
            this.fullName = fullName;
        } else {
            throw new FullNameException(fullName);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws UsernameException {
        if (username.length() > 10) {
            this.username = username;
        } else {
            throw new UsernameException("Invalid input data: ", username);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailException {
        if (validate(email)) {
            this.email = email;
        } else {
            throw new EmailException(email);
        }
    }

    public void setPassword(String password) throws PasswordException, Exception {
        if (password.length() <= 8) {
            throw new PasswordException(password);
        }
        validatePasswordUpperCase(password);
        validatePasswordNums(password);
        passwordHash = password;
    }

    public String toString() {
        return fullName + "," + username + "," + email + "," + passwordHash;
    }

    private static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkPassword(String enteredPassword) {
        return getMd5(enteredPassword).equals(passwordHash);
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    private static boolean validatePasswordNums(String password) throws Exception {
        Pattern patternNums = Pattern.compile("[0-9].*[0-9].*[0-9]");
        Matcher matcherNums = patternNums.matcher(password);
        if (matcherNums.find()) {
            return true;
        }
        throw new Exception("Inserted password doesn't contain numbers or not sufficient quantity.");
    }

    private static boolean validatePasswordUpperCase(String password) throws Exception {
        Pattern patternUpperCase = Pattern.compile("[A-Z].*[A-Z]");
        Matcher matcherUpperCase = patternUpperCase.matcher(password);
        if (matcherUpperCase.find()) {
            return true;
        }
        throw new Exception("Inserted password doesn't contain UpperCase letters /at least 2/.");
    }
}
