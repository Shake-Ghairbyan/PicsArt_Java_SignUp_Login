package ExceptionsForRegistration;

public class FullNameException extends Exception {
    public FullNameException(String fullName) {
        super("Wrong input for full name: " + fullName);
    }
}
