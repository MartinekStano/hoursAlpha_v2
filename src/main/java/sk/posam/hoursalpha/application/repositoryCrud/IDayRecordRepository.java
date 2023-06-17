package sk.posam.hoursalpha.application.repositoryCrud;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.posam.hoursalpha.domain.DayRecord;

public interface IDayRecordRepository extends JpaRepository<DayRecord, Long> {
}
