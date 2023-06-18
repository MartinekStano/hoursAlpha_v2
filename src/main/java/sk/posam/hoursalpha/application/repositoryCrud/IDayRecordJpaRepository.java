package sk.posam.hoursalpha.application.repositoryCrud;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.posam.hoursalpha.domain.DayRecord;
import sk.posam.hoursalpha.domain.Employee;

import java.time.LocalDate;
import java.util.Optional;

public interface IDayRecordJpaRepository extends JpaRepository<DayRecord, Long> {

    Optional<DayRecord> findByDate(LocalDate date);

    Optional<DayRecord> findByEmployeeAndDate(Employee e, LocalDate date);
}
