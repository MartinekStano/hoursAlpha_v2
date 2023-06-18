package sk.posam.hoursalpha.application.assembler;

import org.springframework.stereotype.Component;
import sk.posam.hoursalpha.api.dto.EmployeeDto;
import sk.posam.hoursalpha.domain.Employee;

@Component
public class EmployeeAssembler {
    public EmployeeDto toDto(Employee employee){
        EmployeeDto dto = new EmployeeDto();

        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPhoneNumber(employee.getPhoneNumber());
        dto.setAddress(employee.getAddress());
        dto.setZip(employee.getZip());
        dto.setSalary(employee.getSalaryPerHour());
        return dto;
    }

    public Employee fromDto(EmployeeDto dto){
        return new Employee(
             dto.getFirstName(),
             dto.getLastName(),
             dto.getEmail(),
             dto.getPassword(),
             dto.getPhoneNumber(),
             dto.isStatusOfProfile(),
                dto.getZip(),
                dto.getAddress(),
                dto.getSalary()
        );
    }
}
