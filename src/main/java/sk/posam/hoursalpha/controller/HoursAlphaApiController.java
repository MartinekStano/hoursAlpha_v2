package sk.posam.hoursalpha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RestController;
import sk.posam.hoursalpha.api.IHoursAlphaAPI;
import sk.posam.hoursalpha.api.dto.DayRecordDto;
import sk.posam.hoursalpha.api.dto.EmployeeDto;
import sk.posam.hoursalpha.api.dto.SalaryDto;
import sk.posam.hoursalpha.application.IHoursAlphaApiService;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class HoursAlphaApiController implements IHoursAlphaAPI {

    @Autowired
    IHoursAlphaApiService iHoursAlphaApiService;

     /*
    USER PART
     */

    @Override
    public void login() {
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();//get authentication from security context

        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);//logout
        }

        HttpSession session = request.getSession(false);//destroy session
        if (session != null) {
            session.invalidate();
        }

        // delete the JSESSIONID cookie
        Cookie jSessionId = new Cookie("JSESSIONID", "");
        jSessionId.setMaxAge(0);
        jSessionId.setPath("/");
        response.addCookie(jSessionId);

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
    public void resetPassword(String password, Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        iHoursAlphaApiService.resetPassword(password, user.getUsername());
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
    public SalaryDto getCalculateSalary(Authentication authentication, int month, int year) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return iHoursAlphaApiService.getCalculateSalary(user.getUsername(), month, year);
    }
}
