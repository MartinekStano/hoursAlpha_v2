package sk.posam.hoursalpha.application.assembler;

import net.bytebuddy.dynamic.scaffold.TypeWriter;
import org.springframework.stereotype.Component;
import sk.posam.hoursalpha.api.dto.DayRecordDto;
import sk.posam.hoursalpha.api.dto.EmployeeDto;
import sk.posam.hoursalpha.domain.DayRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DayRecordAssembler {

    public DayRecordDto toDto(DayRecord dayRecord){
        DayRecordDto dto = new DayRecordDto();

        dto.year = dayRecord.getYear();
        dto.month = dayRecord.getDate().getMonth().toString();
        dto.date = dayRecord.getDate().format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
        dto.place = dayRecord.getPlace();
        dto.timeFrom = dayRecord.getTimeFrom().toString();
        dto.timeTo = dayRecord.getTimeTo().toString();
        dto.pause = dayRecord.getPause().toString();

        return dto;
    }

    public DayRecordDto toAdvancedDto(DayRecord dayRecord, double totalSalary, double totalHours){
        DayRecordDto dto = new DayRecordDto();

        dto.year = dayRecord.getYear();
        dto.month = dayRecord.getDate().getMonth().toString();
        dto.date = dayRecord.getDate().format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
        dto.place = dayRecord.getPlace();
        dto.timeFrom = dayRecord.getTimeFrom().toString();
        dto.timeTo = dayRecord.getTimeTo().toString();
        dto.pause = dayRecord.getPause().toString();
        dto.totalSalary = totalSalary;
        dto.totalHours = totalHours;

        return dto;
    }

    public DayRecord fromDto(DayRecordDto dto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd-MM-uuuu" );

        return new DayRecord(
                LocalDate.parse(dto.date, formatter).getYear(),
                LocalDate.parse(dto.date, formatter).getMonth().getValue(),
                LocalDate.parse(dto.date, formatter),
                dto.place,
                LocalTime.parse(dto.timeFrom),
                LocalTime.parse(dto.timeFrom),
                LocalTime.parse(dto.pause)
        );
    }

    public List<DayRecordDto> toDto(List<DayRecord> dayRecords){
        return dayRecords.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
