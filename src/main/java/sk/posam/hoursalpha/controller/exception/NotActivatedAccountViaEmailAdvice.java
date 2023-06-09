package sk.posam.hoursalpha.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotActivatedAccountViaEmailAdvice {

    @ResponseBody
    @ExceptionHandler(NotActivatedAccountViaEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handler(NotActivatedAccountViaEmailException ex) {
        return ex.getMessage();
    }
}
