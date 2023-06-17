package sk.posam.hoursalpha.application.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sk.posam.hoursalpha.api.dto.DayRecordDto;
import sk.posam.hoursalpha.domain.Employee;
import sk.posam.hoursalpha.domain.repository.IEmployeeRepository;
import sk.posam.hoursalpha.domain.service.IDayRecordService;

@Service
public class DayRecordServiceImpl implements IDayRecordService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public void createDayRecord(String email, DayRecordDto dayRecordDto) {
        Employee employee = employeeRepository.findEmployeeByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
