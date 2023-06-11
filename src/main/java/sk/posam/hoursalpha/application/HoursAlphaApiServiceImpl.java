package sk.posam.hoursalpha.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.posam.hoursalpha.api.dto.EmployeeDto;
import sk.posam.hoursalpha.domain.service.IEmployeeService;

@Service
public class HoursAlphaApiServiceImpl implements IHoursAlphaApiService{
    @Autowired
    private IEmployeeService employeeService;

    @Override
    public void register(EmployeeDto dto) {
        employeeService.register(dto);
    }

    @Override
    public void activationEmailAddress(String token) {
        employeeService.activationEmailAddress(token);
    }
}
