package sk.posam.hoursalpha.domain.service;

import sk.posam.hoursalpha.domain.Employee;

import javax.mail.MessagingException;

public interface IEmailService {
    void sendVerificationEmailWithToken(Employee employee) throws MessagingException;
}
