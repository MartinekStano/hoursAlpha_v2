package sk.posam.hoursalpha.application.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.posam.hoursalpha.controller.exception.VerificationTokenNotFoundException;
import sk.posam.hoursalpha.domain.Employee;
import sk.posam.hoursalpha.domain.VerificationToken;
import sk.posam.hoursalpha.domain.repository.IVerificationTokenRepository;
import sk.posam.hoursalpha.domain.service.IVerificationTokenService;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Optional;

@Service
public class VerificationTokenService implements IVerificationTokenService {

    @Autowired
    IVerificationTokenRepository iTokenRepository;

    @Override
    public VerificationToken findByToken(String token) {
        Optional<VerificationToken> verificationToken = iTokenRepository.findByToken(token);
        if(verificationToken.isPresent()){
            return verificationToken.get();
        }else{
            throw new VerificationTokenNotFoundException();
        }
    }

    @Override
    public Optional<VerificationToken> findByEmployee(Employee employee) {
        return iTokenRepository.findByEmployee(employee);
    }

    @Override
    public void save(Employee employee, String token) {
        VerificationToken verificationToken = new VerificationToken(token, employee);
        verificationToken.setExpiryDate(calculateExpiryDate(24*60));
        iTokenRepository.saveNewVerification(verificationToken);
    }

    @Override
    public Timestamp calculateExpiryDate(int expiryTime) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiryTime);
        return new Timestamp(cal.getTime().getTime());
    }

    @Override
    public void deleteToken(String token) {
        iTokenRepository.deleteVerificationToken(findByToken(token));
    }
}
