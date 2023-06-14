package sk.posam.hoursalpha.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "year_record", schema = "public")
@Access(AccessType.FIELD)
public class YearRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "yearRecord")
    private List<MonthRecord> listOfMonthRecord;

    @ManyToOne
    @JoinColumn(name = "year_record_id")
    private YearRecord yearRecord;
}
