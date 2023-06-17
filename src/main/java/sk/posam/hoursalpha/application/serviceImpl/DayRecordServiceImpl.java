package sk.posam.hoursalpha.application.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.posam.hoursalpha.api.dto.DayRecordDto;
import sk.posam.hoursalpha.application.assembler.DayRecordAssembler;
import sk.posam.hoursalpha.controller.exception.DayRecordAlreadyExistException;
import sk.posam.hoursalpha.domain.DayRecord;
import sk.posam.hoursalpha.domain.Employee;
import sk.posam.hoursalpha.domain.repository.IDayRecordRepository;
import sk.posam.hoursalpha.domain.repository.IEmployeeRepository;
import sk.posam.hoursalpha.domain.service.IDayRecordService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DayRecordServiceImpl implements IDayRecordService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IDayRecordRepository dayRecordRepository;

    @Autowired
    private DayRecordAssembler dayRecordAssembler;

    @Transactional
    @Override
    public void createDayRecord(String email, DayRecordDto dayRecordDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");

        Employee employee = employeeRepository.findEmployeeByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        if(dayRecordRepository.findByDayRecordByDate(LocalDate.parse(dayRecordDto.date, formatter)).isPresent())
            throw new DayRecordAlreadyExistException();


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

    @Override
    public List<DayRecordDto> getAllDayRecords(String email) {
        Employee employee = employeeRepository.findEmployeeByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));


        return dayRecordAssembler.toDto(employee.getListOfYearRecord());
    }
}
