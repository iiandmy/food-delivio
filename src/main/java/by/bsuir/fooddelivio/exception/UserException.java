package by.bsuir.fooddelivio.exception;

public class UserException extends Exception {
    public UserException() {
        super("User exception");
    }

    public UserException(String message) {
        super(message);
    }
}
