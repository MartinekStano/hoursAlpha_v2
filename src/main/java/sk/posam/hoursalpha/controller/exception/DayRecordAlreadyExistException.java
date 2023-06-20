package sk.posam.hoursalpha.controller.exception;

public class DayRecordAlreadyExistException extends RuntimeException{
    public DayRecordAlreadyExistException(String message) {
        super(message);
    }
}
