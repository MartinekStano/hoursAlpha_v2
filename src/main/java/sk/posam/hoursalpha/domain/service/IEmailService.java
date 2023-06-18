package sk.posam.hoursalpha.domain.service;

import sk.posam.hoursalpha.domain.Employee;

import javax.mail.MessagingException;
import java.util.Optional;

public interface IEmailService {
    void sendVerificationEmailWithToken(Employee employee) throws MessagingException;

    void sendResetPasswordViaEmail(Employee employee) throws MessagingException;

    void sendNotificationToEmployee(String email) throws MessagingException;
}
