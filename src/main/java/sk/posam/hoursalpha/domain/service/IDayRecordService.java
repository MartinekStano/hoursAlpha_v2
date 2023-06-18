package sk.posam.hoursalpha.domain.service;

import sk.posam.hoursalpha.api.dto.DayRecordDto;
import sk.posam.hoursalpha.api.dto.SalaryDto;

import java.util.List;

public interface IDayRecordService {
    void createDayRecord(String email, DayRecordDto dayRecordDto);

    List<DayRecordDto> getAllDayRecords(String email);

    void sendNotificationIfDayRecordDoesntExist();

    void recordDefaultRecord();

    void editDayRecord(String email, DayRecordDto dayRecordDto);

    SalaryDto getCalculateSalary(String email, int month, int year);
}
