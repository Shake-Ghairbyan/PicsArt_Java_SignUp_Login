package ExceptionsForRegistration;

public class UsernameException extends Exception {
    public UsernameException(String message, String username) {
        super(message + username);
    }
}
