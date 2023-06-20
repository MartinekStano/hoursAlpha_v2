package sk.posam.hoursalpha.controller.exception;

public class EmailIsUnavailableException extends RuntimeException{
    public EmailIsUnavailableException(String message) {
        super(message);
    }
}
