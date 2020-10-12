package mx.com.axity.commons.exceptions;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

}
