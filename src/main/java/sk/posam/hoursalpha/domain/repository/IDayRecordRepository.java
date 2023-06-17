package sk.posam.hoursalpha.domain.repository;

import sk.posam.hoursalpha.domain.DayRecord;

import java.util.Optional;

public interface IDayRecordRepository {
    DayRecord saveNewDayRecord(DayRecord dayRecord);
}
