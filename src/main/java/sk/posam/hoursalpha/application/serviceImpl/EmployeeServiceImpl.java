package sk.posam.hoursalpha.application.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.posam.hoursalpha.api.dto.EmployeeDto;
import sk.posam.hoursalpha.controller.exception.EmailIsUnavailableException;
import sk.posam.hoursalpha.domain.Employee;
import sk.posam.hoursalpha.domain.repository.IEmployeeRepository;
import sk.posam.hoursalpha.domain.service.IEmailService;
import sk.posam.hoursalpha.domain.service.IEmployeeService;
import sk.posam.hoursalpha.domain.service.IVerificationTokenService;

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

                    System.out.println("Something");

                    emailService.sendVerificationEmailWithToken(u);

                    System.out.println("what");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            });
        }

    }

    @Override
    public void activationEmailAddress(String tokeb) {

    }
}
