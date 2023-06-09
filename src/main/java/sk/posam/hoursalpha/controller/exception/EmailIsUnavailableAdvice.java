package sk.posam.hoursalpha.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class EmailIsUnavailableAdvice {
    @ResponseBody
    @ExceptionHandler(EmailIsUnavailableException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String handler(EmailIsUnavailableException ex) {
        return ex.getMessage();
    }
}
