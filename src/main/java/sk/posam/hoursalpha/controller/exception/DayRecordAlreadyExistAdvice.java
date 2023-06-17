package sk.posam.hoursalpha.controller.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DayRecordAlreadyExistAdvice {
    @ResponseBody
    @ExceptionHandler(DayRecordAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String handler(DayRecordAlreadyExistException ex) {
        return ex.getMessage();
    }
}
