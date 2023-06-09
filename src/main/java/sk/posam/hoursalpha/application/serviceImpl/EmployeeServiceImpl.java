package sk.posam.hoursalpha.application.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.posam.hoursalpha.api.dto.EmployeeDto;
import sk.posam.hoursalpha.controller.exception.EmailIsUnavailableException;
import sk.posam.hoursalpha.domain.repository.IEmployeeRepository;
import sk.posam.hoursalpha.domain.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;
    @Override
    public void register(EmployeeDto dto) {

        if(employeeRepository.findEmployeeByEmail(dto.getEmail()).isPresent()){
            throw new EmailIsUnavailableException();
        }else{

        }

    }
}
