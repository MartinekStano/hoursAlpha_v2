package sk.posam.hoursalpha.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.posam.hoursalpha.api.dto.EmployeeDto;
import sk.posam.hoursalpha.domain.service.IEmployeeService;

import javax.mail.MessagingException;

@Service
public class HoursAlphaApiServiceImpl implements IHoursAlphaApiService{
    @Autowired
    private IEmployeeService employeeService;

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
    public void sendResetPassword(String email) {
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
}
