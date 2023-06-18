package sk.posam.hoursalpha.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.posam.hoursalpha.api.dto.DayRecordDto;
import sk.posam.hoursalpha.api.dto.EmployeeDto;
import sk.posam.hoursalpha.api.dto.SalaryDto;
import sk.posam.hoursalpha.domain.service.IDayRecordService;
import sk.posam.hoursalpha.domain.service.IEmployeeService;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class HoursAlphaApiServiceImpl implements IHoursAlphaApiService{
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IDayRecordService dayRecordService;

    @Override
    public void register(EmployeeDto dto) {
        employeeService.register(dto);
    }

    @Override
    public void activationEmailAddress(String token) {
        employeeService.activationEmailAddress(token);
    }

    @Override
    public void resendVerificationEmail(String email) throws MessagingException {
        employeeService.resendVerificationEmail(email);
    }

    @Override
    public void sendResetPassword(String email) throws MessagingException {
        employeeService.sendResetPassword(email);
    }

    @Override
    public void resetPassword(String password, String email) {
        employeeService.resetPassword(password, email);
    }

    @Override
    public void updateEmployeeProfile(EmployeeDto employeeDto, String email) {
        employeeService.updateEmployeeProfile(employeeDto, email);
    }

    @Override
    public void resetPasswordViaEmail(String password, String token) {
        employeeService.resetPasswordViaEmail(password, token);
    }

    @Override
    public EmployeeDto getEmployeeDetails(String email) {
       return employeeService.getEmployeeDetails(email);
    }

    /*
    DAY RECORD PART
     */

    @Override
    public void createDayRecord(String email, DayRecordDto dayRecordDto) {
        dayRecordService.createDayRecord(email, dayRecordDto);
    }

    @Override
    public List<DayRecordDto> getAllDayRecords(String email) {
        return dayRecordService.getAllDayRecords(email);
    }

    @Override
    public void editDayRecord(String email, DayRecordDto dayRecordDto) {
        dayRecordService.editDayRecord(email, dayRecordDto);
    }

    @Override
    public SalaryDto getCalculateSalary(String email, int month, int year) {
        return dayRecordService.getCalculateSalary(email, month, year);
    }
}
