package by.bsuir.fooddelivio.exception;

public class UserWrongPasswordException extends UserException {
    public UserWrongPasswordException() {
        super("Wrong password");
    }

    public UserWrongPasswordException(String message) {
        super(message);
    }
}
