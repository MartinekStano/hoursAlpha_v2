package sk.posam.hoursalpha.domain;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "day_record", schema = "public")
@Access(AccessType.FIELD)
public class DayRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "month")
    private Integer month;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "place")
    private String place;

    @Column(name = "time_from")
    private LocalTime timeFrom;
    @Column(name = "time_to")
    private LocalTime timeTo;

    @Column(name = "pause")
    private LocalTime pause;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public DayRecord(Integer year, Integer month, LocalDate date, String place, LocalTime timeFrom, LocalTime timeTo, LocalTime pause) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.place = place;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.pause = pause;
    }

    public DayRecord() {
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    public LocalTime getPause() {
        return pause;
    }

    public Integer getYear() {
        return year;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Integer getMonth() {
        return month;
    }
}
