package sk.posam.hoursalpha.domain.service;

import sk.posam.hoursalpha.api.dto.DayRecordDto;

public interface IDayRecordService {
    void createDayRecord(String email, DayRecordDto dayRecordDto);
}
