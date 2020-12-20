import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String fullName;
    private String username;
    private String email;
    private String passwordHash;

    public User(String s) {
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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.passwordHash = getMd5(password);
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

    public boolean checkPassword(String enteredPassword){
        return getMd5(enteredPassword).equals(passwordHash);
    }
}
