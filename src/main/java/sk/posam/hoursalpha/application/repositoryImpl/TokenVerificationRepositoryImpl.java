package sk.posam.hoursalpha.application.repositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.posam.hoursalpha.application.repositoryCrud.ITokenJpaRepository;
import sk.posam.hoursalpha.domain.Employee;
import sk.posam.hoursalpha.domain.VerificationToken;
import sk.posam.hoursalpha.domain.repository.IVerificationTokenRepository;

import java.util.Optional;

@Repository
public class TokenVerificationRepositoryImpl implements IVerificationTokenRepository {

    @Autowired
    ITokenJpaRepository iTokenJpaRepository;

    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return iTokenJpaRepository.findByToken(token);
    }

    @Override
    public Optional<VerificationToken> findByEmployee(Employee employee) {
        return iTokenJpaRepository.findByEmployee(employee);
    }

    @Override
    public void saveNewVerification(VerificationToken verificationToken) {
        iTokenJpaRepository.save(verificationToken);
    }

    @Override
    public void deleteVerificationToken(VerificationToken verificationToken) {
        iTokenJpaRepository.delete(verificationToken);
    }
}
