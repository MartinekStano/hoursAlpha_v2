package sk.posam.hoursalpha.application.serviceImpl;

import org.springframework.stereotype.Service;
import sk.posam.hoursalpha.domain.Employee;
import sk.posam.hoursalpha.domain.service.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {
    @Override
    public void sendVerificationEmailWithToken(Employee employee) {

    }
}
