package sk.posam.hoursalpha.domain.repository;

import sk.posam.hoursalpha.domain.Employee;
import sk.posam.hoursalpha.domain.VerificationToken;

import java.util.Optional;

public interface IVerificationTokenRepository {
    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> findByEmployee(Employee employee);

    void saveNewVerification(VerificationToken verificationToken);
    void deleteVerificationToken(VerificationToken verificationToken);
}
