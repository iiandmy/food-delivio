package by.bsuir.fooddelivio.exception;

public class UserAlreadyExistsException extends UserException {
    public UserAlreadyExistsException() {
        super("User already exists");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
