package sk.posam.hoursalpha.controller.exception;

public class NotActivatedAccountViaEmailException extends  RuntimeException{
    public NotActivatedAccountViaEmailException(String message) {
        super(message);
    }
}
