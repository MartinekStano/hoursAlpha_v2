package sk.posam.hoursalpha.application.repositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.posam.hoursalpha.application.repositoryCrud.IDayRecordJpaRepository;
import sk.posam.hoursalpha.domain.DayRecord;
import sk.posam.hoursalpha.domain.repository.IDayRecordRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public class DayRecordRepositoryImpl implements IDayRecordRepository {


    @Autowired
    private IDayRecordJpaRepository dayRecordJpaRepository;

    @Override
    public DayRecord saveNewDayRecord(DayRecord dayRecord) {
        return dayRecordJpaRepository.save(dayRecord);
    }
}
