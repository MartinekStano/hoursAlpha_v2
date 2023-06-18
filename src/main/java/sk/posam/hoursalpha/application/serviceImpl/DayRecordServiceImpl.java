package sk.posam.hoursalpha.application.serviceImpl;

import net.bytebuddy.asm.Advice;
import org.hibernate.type.DateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.posam.hoursalpha.api.dto.DayRecordDto;
import sk.posam.hoursalpha.api.dto.SalaryDto;
import sk.posam.hoursalpha.application.assembler.DayRecordAssembler;
import sk.posam.hoursalpha.controller.exception.BadRequestException;
import sk.posam.hoursalpha.controller.exception.DayRecordAlreadyExistException;
import sk.posam.hoursalpha.domain.DayRecord;
import sk.posam.hoursalpha.domain.Employee;
import sk.posam.hoursalpha.domain.repository.IDayRecordRepository;
import sk.posam.hoursalpha.domain.repository.IEmployeeRepository;
import sk.posam.hoursalpha.domain.service.IDayRecordService;
import sk.posam.hoursalpha.domain.service.IEmailService;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

import static java.time.temporal.ChronoUnit.MINUTES;


@Service
public class DayRecordServiceImpl implements IDayRecordService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IDayRecordRepository dayRecordRepository;

    @Autowired
    private DayRecordAssembler dayRecordAssembler;

    @Autowired
    private IEmailService emailService;

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

        List<DayRecordDto> listOfAdvancedDayRecords = new ArrayList<>();

        employee.getListOfYearRecord().forEach(r -> {
            double pause = MINUTES.between(LocalTime.parse("00:00"), r.getPause());
            double totalHours  = (MINUTES.between(r.getTimeFrom(), r.getTimeTo()))-pause;
            totalHours /= 60;

            double totalSalary = totalHours*employee.getSalaryPerHour();


            listOfAdvancedDayRecords.add(dayRecordAssembler.toAdvancedDto(r,totalSalary,totalHours ));

        });

        return listOfAdvancedDayRecords;

    }

    @Override
    @Transactional
    public void editDayRecord(String email, DayRecordDto dayRecordDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");

        DayRecord dayRecord = dayRecordRepository.findByDayRecordByDate(LocalDate.parse(dayRecordDto.date, formatter))
                .orElseThrow(BadRequestException::new);

        saveIfNotEmpty(dayRecordDto.place, dayRecord, DayRecord::setPlace);
        saveIfNotEmpty(LocalTime.parse(dayRecordDto.timeTo), dayRecord, DayRecord::setTimeTo);
        saveIfNotEmpty(LocalTime.parse(dayRecordDto.timeFrom), dayRecord, DayRecord::setTimeFrom);
        saveIfNotEmpty(LocalTime.parse(dayRecordDto.pause), dayRecord, DayRecord::setPause);

    }

    public <T> void saveIfNotEmpty(T toBeSet, DayRecord dayRecord, BiConsumer<DayRecord, T> setter){
        if(toBeSet != null)
            setter.accept(dayRecord, toBeSet);
    }

    @Override
    public SalaryDto getCalculateSalary(String email, int month, int year) {

        Employee employee = employeeRepository.findEmployeeByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User was not found!"));

        List<DayRecord> dayRecords = dayRecordRepository.findByEmployeeAndMonthAndYear(employee, month, year);

        SalaryDto salaryDto = new SalaryDto(0,0,0);

        dayRecords.forEach(r -> {
            salaryDto.totalHours += calculateWorkTime(r.getTimeFrom(), r.getTimeTo(), r.getPause());
        });

        salaryDto.totalSalary = salaryDto.totalHours*employee.getSalaryPerHour();
        salaryDto.clearSalary = calculateClearSalary(salaryDto);


        return salaryDto;
    }

    public double calculateWorkTime(LocalTime timeFromDB, LocalTime timeToDB, LocalTime pauseDB){

        double pause = MINUTES.between(LocalTime.parse("00:00"), pauseDB);
        double totalHours  = (MINUTES.between(timeFromDB, timeToDB))-pause;
        totalHours /= 60;

        return totalHours;
    }

    public double calculateClearSalary(SalaryDto salaryDto){
        double levies = salaryDto.totalSalary*0.134;
        double tax = (salaryDto.totalSalary - levies) - 410.24;
        tax *= 0.19;


        return salaryDto.totalSalary - levies - tax;
    }

    @Override
    public void sendNotificationIfDayRecordDoesntExist() {
        List<Employee> listOfEmployee = employeeRepository.getAllEmployee();


        listOfEmployee.forEach(e -> {
            Optional<DayRecord> dayRecord = dayRecordRepository.findByDayRecordByEmployeeAndDate(e, LocalDate.now());

            if (dayRecord.isEmpty()) {
                try {
                    emailService.sendNotificationToEmployee(e.getEmail());
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                    throw new BadRequestException();
                }
            }


        });

    }

    @Override
    @Transactional
    public void recordDefaultRecord() {
        List<Employee> listOfEmployee = employeeRepository.getAllEmployee();

        listOfEmployee.forEach(e -> {
            Optional<DayRecord> dayRecord = dayRecordRepository.findByDayRecordByEmployeeAndDate(e, LocalDate.now());

            if(dayRecord.isEmpty()){
                DayRecord defaultDayRecord = new DayRecord(
                        LocalDate.now().getYear(),
                        LocalDate.now().getMonthValue(),
                        LocalDate.now(),
                        "Undefined",
                        LocalTime.parse("00:00"),
                        LocalTime.parse("00:00"),
                        LocalTime.parse("00:00"),
                        e
                );

            e.getListOfYearRecord().add(dayRecordRepository.saveNewDayRecord(defaultDayRecord));
            }

        });
    }
}
