package project.board_service.exception;

public class PasswordCheckFailedException extends RuntimeException {
    public PasswordCheckFailedException(String message) {
        super(message);

    }
}
