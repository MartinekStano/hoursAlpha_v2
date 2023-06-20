package sk.posam.hoursalpha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import sk.posam.hoursalpha.api.IHoursAlphaAPI;
import sk.posam.hoursalpha.api.dto.*;
import sk.posam.hoursalpha.application.IHoursAlphaApiService;
import sk.posam.hoursalpha.controller.exception.BadRequestException;
import sk.posam.hoursalpha.security.AuthenticationService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class HoursAlphaApiController implements IHoursAlphaAPI {

    @Autowired
    IHoursAlphaApiService iHoursAlphaApiService;

    @Autowired
    private AuthenticationService authenticationService;
     /*
    USER PART
     */

    @Override
    public void login(LoginDto loginDto) {
        try {
            authenticationService.authenticate(loginDto.email, loginDto.password);
            System.out.println("Login succesfull");
        } catch (BadCredentialsException ex) {
            System.out.println("Invalid username or password");
            throw new BadCredentialsException("Bad credentials");
        }
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
    public void sendResetPasswordEmail(String email) throws MessagingException {
        iHoursAlphaApiService.sendResetPassword(email);
    }

    @Override
    public void resetPassword(String newPassword, Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        iHoursAlphaApiService.resetPassword(newPassword, user.getUsername());
    }

    @Override
    public void updateEmployeeProfile(EmployeeDto employeeDto, Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        iHoursAlphaApiService.updateEmployeeProfile(employeeDto, user.getUsername());
    }

    @Override
    public void resetPasswordViaEmail(String password, String token) {
        iHoursAlphaApiService.resetPasswordViaEmail(password, token);
    }

    @Override
    public EmployeeDto getEmployeeDetails(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return iHoursAlphaApiService.getEmployeeDetails(user.getUsername());
    }

    @Override
    public void deleteAccount(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        iHoursAlphaApiService.deleteAccount(user.getUsername());
    }

    @Override
    public SalaryCalculatorDto getCalculatedSalaryWithParam(SalaryCalculatorDto salaryCalculatorDto) {
        return iHoursAlphaApiService.getCalculatedSalaryWithParam(salaryCalculatorDto);
    }

    /*
    DAY RECORD PART
     */

    @Override
    public void createDayRecord(DayRecordDto dayRecordDto, Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        iHoursAlphaApiService.createDayRecord(user.getUsername(), dayRecordDto);
    }

    @Override
    public List<DayRecordDto> getAllDayRecords(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return iHoursAlphaApiService.getAllDayRecords(user.getUsername());
    }

    @Override
    public void editDayRecord(DayRecordDto dayRecordDto, Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        iHoursAlphaApiService.editDayRecord(user.getUsername(), dayRecordDto);
    }

    @Override
    public SalaryDto getCalculateSalary(Authentication authentication, String date) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return iHoursAlphaApiService.getCalculateSalary(user.getUsername(), date);
    }

    @Override
    public List<DayRecordDto> getAllDayRecordsCurrentMonth(String date, Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return iHoursAlphaApiService.getAllDayRecordsCurrentMonth(user.getUsername(), date);
    }
}
