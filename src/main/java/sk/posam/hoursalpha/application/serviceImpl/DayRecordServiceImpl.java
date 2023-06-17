package sk.posam.hoursalpha.application.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.posam.hoursalpha.api.dto.DayRecordDto;
import sk.posam.hoursalpha.domain.DayRecord;
import sk.posam.hoursalpha.domain.Employee;
import sk.posam.hoursalpha.domain.repository.IDayRecordRepository;
import sk.posam.hoursalpha.domain.repository.IEmployeeRepository;
import sk.posam.hoursalpha.domain.service.IDayRecordService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class DayRecordServiceImpl implements IDayRecordService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IDayRecordRepository dayRecordRepository;

    @Transactional
    @Override
    public void createDayRecord(String email, DayRecordDto dayRecordDto) {
        Employee employee = employeeRepository.findEmployeeByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");

        DayRecord newDayRecord = new DayRecord(
                LocalDate.parse(dayRecordDto.date, formatter).getYear(),
                LocalDate.parse(dayRecordDto.date, formatter).getMonthValue(),
                LocalDate.parse(dayRecordDto.date, formatter),
                dayRecordDto.place,
                LocalTime.parse(dayRecordDto.timeFrom),
                LocalTime.parse(dayRecordDto.timeTo),
                LocalTime.parse(dayRecordDto.pause),
                employee
        );

        employee.getListOfYearRecord().add(dayRecordRepository.saveNewDayRecord(newDayRecord));

    }
}
