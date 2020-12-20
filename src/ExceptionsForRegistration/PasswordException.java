package ExceptionsForRegistration;

public class PasswordException extends Exception {
    public PasswordException (String password){
        super("Invalid input for password.");
    }
}
