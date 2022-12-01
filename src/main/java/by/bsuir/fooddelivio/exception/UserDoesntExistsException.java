package by.bsuir.fooddelivio.exception;

public class UserDoesntExistsException extends UserException {
    public UserDoesntExistsException() {
        super("User Doesn't exists exception");
    }

    public UserDoesntExistsException(String message) {
        super(message);
    }
}
