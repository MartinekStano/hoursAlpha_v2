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

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "place")
    private String place;

    @Column(name = "time_from")
    private LocalTime timeFrom;
    @Column(name = "time_to")
    private LocalTime timeTo;

    @Column(name = "salary_per_hour")
    private double salaryPerHour;

    @Column(name = "pause")
    private LocalTime pause;

    @ManyToOne
    @JoinColumn(name = "month_record_id")
    private MonthRecord monthRecord;
}
