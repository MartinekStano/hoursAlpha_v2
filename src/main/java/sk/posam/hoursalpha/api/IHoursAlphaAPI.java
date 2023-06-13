package sk.posam.hoursalpha.api;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sk.posam.hoursalpha.api.dto.EmployeeDto;

import javax.mail.MessagingException;

public interface IHoursAlphaAPI {

    @PostMapping("/Auth/login")
    Long login();

    @PostMapping("/noAuth/register")
    void register(@RequestBody EmployeeDto dto);

    @PostMapping("/noAuth/verify/{token}")
    void activationEmailAddress(@PathVariable String token);

    @PostMapping("/noAuth/resendVerificationEmail")
    void resendVerificationEmail(@RequestParam String email) throws MessagingException;

    @PostMapping("/noAuth/sendResetPasswordEmail")
    void sendResetPasswordEmail(@RequestParam String email);

    @PostMapping("/Auth/resetPassword")
    void resetPassword(@RequestParam String password, Authentication authentication);

    @PutMapping("/Auth/updateEmployeeProfile")
    void updateEmployeeProfile(EmployeeDto employeeDto, Authentication authentication);
}
