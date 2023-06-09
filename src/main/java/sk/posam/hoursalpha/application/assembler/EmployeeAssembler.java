package sk.posam.hoursalpha.application.assembler;

import sk.posam.hoursalpha.api.dto.EmployeeDto;
import sk.posam.hoursalpha.domain.Employee;

public class EmployeeAssembler {
    public EmployeeDto toDto(Employee employee){
        EmployeeDto dto = new EmployeeDto();

        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPhoneNumber(employee.getPhoneNumber());
        return dto;
    }

    public Employee fromDto(EmployeeDto dto){
        return new Employee(
             dto.getFirstName(),
             dto.getLastName(),
             dto.getEmail(),
             dto.getPassword(),
             dto.getPhoneNumber(),
             dto.isStatusOfProfile()
        );
    }
}
