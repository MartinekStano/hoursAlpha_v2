package sk.posam.hoursalpha.application;

import sk.posam.hoursalpha.api.dto.DayRecordDto;
import sk.posam.hoursalpha.api.dto.EmployeeDto;
import sk.posam.hoursalpha.api.dto.SalaryDto;

import javax.mail.MessagingException;
import java.util.List;

public interface IHoursAlphaApiService {
    void register(EmployeeDto dto);

    void activationEmailAddress(String token);
    void resendVerificationEmail(String email) throws MessagingException;
    void sendResetPassword(String email) throws MessagingException;
    void resetPasswordViaEmail(String password, String token);
    void resetPassword(String password, String email);
    void updateEmployeeProfile(EmployeeDto employeeDto, String email);

    EmployeeDto getEmployeeDetails(String email);

    void createDayRecord(String email, DayRecordDto dayRecordDto);

    List<DayRecordDto> getAllDayRecords(String email);

    void editDayRecord(String email, DayRecordDto dayRecordDto);

    SalaryDto getCalculateSalary(String email, int month, int year);
}
