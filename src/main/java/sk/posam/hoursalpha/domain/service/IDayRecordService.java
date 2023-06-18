package sk.posam.hoursalpha.domain.service;

import sk.posam.hoursalpha.api.dto.DayRecordDto;

import java.util.List;

public interface IDayRecordService {
    void createDayRecord(String email, DayRecordDto dayRecordDto);

    List<DayRecordDto> getAllDayRecords(String email);

    void sendNotificationIfDayRecordDoesntExist();

    void recordDefaultRecord();
}
