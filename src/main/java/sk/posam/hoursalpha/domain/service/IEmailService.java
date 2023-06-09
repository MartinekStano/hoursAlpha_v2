package sk.posam.hoursalpha.domain.service;

import sk.posam.hoursalpha.domain.Employee;

public interface IEmailService {
    void sendVerificationEmailWithToken(Employee employee);
}
