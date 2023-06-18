package sk.posam.hoursalpha.domain.repository;

import sk.posam.hoursalpha.domain.DayRecord;
import sk.posam.hoursalpha.domain.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IDayRecordRepository {
    DayRecord saveNewDayRecord(DayRecord dayRecord);

    Optional<DayRecord> findByDayRecordByDate(LocalDate date);

    Optional<DayRecord> findByDayRecordByEmployeeAndDate(Employee e, LocalDate date);

    List<DayRecord> findByEmployeeAndMonthAndYear(Employee employee, int month, int year);
}
