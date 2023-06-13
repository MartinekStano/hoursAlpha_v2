package sk.posam.hoursalpha.application.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.posam.hoursalpha.api.dto.EmployeeDto;
import sk.posam.hoursalpha.controller.exception.EmailIsUnavailableException;
import sk.posam.hoursalpha.controller.exception.TokenExpiredException;
import sk.posam.hoursalpha.domain.Employee;
import sk.posam.hoursalpha.domain.VerificationToken;
import sk.posam.hoursalpha.domain.repository.IEmployeeRepository;
import sk.posam.hoursalpha.domain.service.IEmailService;
import sk.posam.hoursalpha.domain.service.IEmployeeService;
import sk.posam.hoursalpha.domain.service.IVerificationTokenService;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    IVerificationTokenService iVerificationTokenService;

    @Autowired
    IEmailService emailService;

    @Override
    public void register(EmployeeDto dto) {

        if(employeeRepository.findEmployeeByEmail(dto.getEmail()).isPresent()){
            throw new EmailIsUnavailableException();
        }else{
            Optional<Employee> savedEmployee = employeeRepository.saveNewEmployee(new Employee(
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getEmail(),
                    encoder.encode(dto.getPassword()),
                    dto.getPhoneNumber(),
                    false
            ));

            savedEmployee.ifPresent(u -> {
                try{
                    String token = UUID.randomUUID().toString();
                    iVerificationTokenService.save(savedEmployee.get(),token);

                    emailService.sendVerificationEmailWithToken(u);

                }catch (Exception e){
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            });
        }

    }

    @Override
    @Transactional
    public void activationEmailAddress(String token) {
        VerificationToken verificationToken = iVerificationTokenService.findByToken(token);

        Employee employee = verificationToken.getEmployee();

        if(!employee.isStatusOfProfile()) {
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

            if(verificationToken.getExpiryDate().before(currentTimestamp)){
                throw new TokenExpiredException();
            }else{
                employee.setStatusOfProfile(true);
                iVerificationTokenService.deleteToken(token);
            }
        }
    }

    @Override
    public void resendVerificationEmail(String email) throws MessagingException {
        Optional<Employee> employee = employeeRepository.findEmployeeByEmail(email);

        if(employee.isPresent()) {
            Optional<VerificationToken> verificationToken = iVerificationTokenService.findByEmployee(employee.get());

            verificationToken.ifPresent(token -> iVerificationTokenService.deleteToken(token.getToken()));

            iVerificationTokenService.save(employee.get(), UUID.randomUUID().toString());
            emailService.sendVerificationEmailWithToken(employee.get());

        }else {
            throw new UsernameNotFoundException("User was not found!");
        }
    }

    @Override
    public void sendResetPassword(String email) throws MessagingException {
        Optional<Employee> employee = employeeRepository.findEmployeeByEmail(email);

        if(employee.isPresent()) {
            iVerificationTokenService.findByEmployee(employee.get()).ifPresent(token -> iVerificationTokenService.deleteToken(token.getToken()));

            iVerificationTokenService.save(employee.get(), UUID.randomUUID().toString());

            emailService.sendResetPasswordViaEmail(employee.get());
        }else {
            throw new UsernameNotFoundException("User was not found!");
        }
    }

    @Override
    public void resetPassword(String password, String email) {

    }

    @Override
    public void updateEmployeeProfile(EmployeeDto employeeDto, String email) {

    }

    @Override
    public void resetPasswordViaEmail(String password, String token) {

    }
}
