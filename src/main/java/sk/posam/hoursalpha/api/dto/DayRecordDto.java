package sk.posam.hoursalpha.api.dto;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.criteria.CriteriaBuilder;

public class DayRecordDto {

    public String date;
    public String place;

    public Integer year;
    public Integer month;

    public String timeFrom;
    public String timeTo;

    public String pause;
}
