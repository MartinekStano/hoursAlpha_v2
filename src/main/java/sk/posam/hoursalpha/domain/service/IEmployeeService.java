package sk.posam.hoursalpha.domain.service;

import sk.posam.hoursalpha.api.dto.EmployeeDto;

import javax.mail.MessagingException;

public interface IEmployeeService {
    void register(EmployeeDto dto);

    void activationEmailAddress(String token);

    void resendVerificationEmail(String email) throws MessagingException;
    void sendResetPassword(String email) throws MessagingException;
    void resetPasswordViaEmail(String password, String token);
    void resetPassword(String password, String email);

    void updateEmployeeProfile(EmployeeDto employeeDto, String email);

    EmployeeDto getEmployeeDetails(String email);
}
