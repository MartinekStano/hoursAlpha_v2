package sk.posam.hoursalpha.api;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sk.posam.hoursalpha.api.dto.DayRecordDto;
import sk.posam.hoursalpha.api.dto.EmployeeDto;
import sk.posam.hoursalpha.api.dto.LoginDto;
import sk.posam.hoursalpha.api.dto.SalaryDto;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.List;

public interface IHoursAlphaAPI {


    /*
    EMPLOYEE PART
     */

    @PostMapping("/Auth/login")
    void login(@RequestBody LoginDto loginDto);

    @PostMapping("/noAuth/register")
    void register(@RequestBody EmployeeDto dto);

    @PostMapping("/noAuth/afterVerifyEmail/{token}")
    void activationEmailAddress(@PathVariable String token);

    @PostMapping("/noAuth/resendVerificationEmail/{email}")
    void resendVerificationEmail(@PathVariable String email) throws MessagingException;

    @PostMapping("/noAuth/sendResetPasswordEmail/{email}")
    void sendResetPasswordEmail(@PathVariable String email) throws MessagingException;

    @PutMapping("/noAuth/resetPassword/{token}")
    void resetPasswordViaEmail(@RequestParam String password, @PathVariable String token);

    @PutMapping("/Auth/resetPassword")
    void resetPassword(@RequestParam String newPassword, Authentication authentication);

    @PutMapping("/Auth/updateEmployeeProfile")
    void updateEmployeeProfile(@RequestBody(required = false) EmployeeDto employeeDto, Authentication authentication);

    @GetMapping("/Auth/employeeDetails")
    EmployeeDto getEmployeeDetails(Authentication authentication);

    /*
    DAY RECORD PART
     */

    @PostMapping("/Auth/createDayRecord")
    void createDayRecord(@RequestBody(required = false) DayRecordDto dayRecordDto, Authentication authentication);

    @GetMapping("/Auth/getAllDayRecords")
    List<DayRecordDto> getAllDayRecords(Authentication authentication);

    @PutMapping("/Auth/editDayRecords")
    void editDayRecord(@RequestBody(required = false) DayRecordDto dayRecordDto, Authentication authentication);

    @GetMapping("/Auth/calculatorSalary")
    SalaryDto getCalculateSalary(Authentication authentication, @RequestParam int month, @RequestParam int year);

}
