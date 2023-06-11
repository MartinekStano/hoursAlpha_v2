package sk.posam.hoursalpha.domain.service;

import sk.posam.hoursalpha.domain.Employee;
import sk.posam.hoursalpha.domain.VerificationToken;

import java.sql.Timestamp;

public interface IVerificationTokenService {
    VerificationToken findByToken(String token);

    VerificationToken findByEmployee(Employee employee);

    void save(Employee employee, String token);

    Timestamp calculateExpiryDate(int expiryTime);

    void deleteToken(String token);
}
