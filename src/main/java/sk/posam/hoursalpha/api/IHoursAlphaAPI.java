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

    @PostMapping("/noAuth/afterVerifyEmail/{token}")
    void activationEmailAddress(@PathVariable String token);

    @PostMapping("/noAuth/resendVerificationEmail")
    void resendVerificationEmail(@RequestParam String email) throws MessagingException;

    @PostMapping("/noAuth/sendResetPasswordEmail")
    void sendResetPasswordEmail(@RequestParam String email) throws MessagingException;

    @PutMapping("/noAuth/resetPassword/{token}")
    void resetPasswordViaEmail(@RequestParam String password, @PathVariable String token);

    @PostMapping("/Auth/resetPassword")
    void resetPassword(@RequestParam String password, Authentication authentication);

    @PutMapping("/Auth/updateEmployeeProfile")
    void updateEmployeeProfile(@RequestBody(required = false) EmployeeDto employeeDto, Authentication authentication);

    @GetMapping("/Auth/employeeDetails")
    EmployeeDto getEmployeeDetails(Authentication authentication);
}
