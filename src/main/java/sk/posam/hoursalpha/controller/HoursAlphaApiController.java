package sk.posam.hoursalpha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import sk.posam.hoursalpha.api.IHoursAlphaAPI;
import sk.posam.hoursalpha.api.dto.EmployeeDto;
import sk.posam.hoursalpha.application.IHoursAlphaApiService;

import javax.mail.MessagingException;

@RestController
public class HoursAlphaApiController implements IHoursAlphaAPI {

    @Autowired
    IHoursAlphaApiService iHoursAlphaApiService;

    @Override
    public Long login() {
        return 1L;
    }

    @Override
    public void register(EmployeeDto dto) {
        iHoursAlphaApiService.register(dto);
    }

    @Override
    public void activationEmailAddress(String token) {
        iHoursAlphaApiService.activationEmailAddress(token);
    }

    @Override
    public void resendVerificationEmail(String email) throws MessagingException {
        iHoursAlphaApiService.resendVerificationEmail(email);
    }

    @Override
    public void sendResetPasswordEmail(String email) {
        iHoursAlphaApiService.sendResetPassword(email);
    }

    @Override
    public void resetPassword(String password, Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        iHoursAlphaApiService.resetPassword(password, user.getUsername());
    }

    @Override
    public void updateEmployeeProfile(EmployeeDto employeeDto, Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        iHoursAlphaApiService.updateEmployeeProfile(employeeDto, user.getUsername());
    }
}
