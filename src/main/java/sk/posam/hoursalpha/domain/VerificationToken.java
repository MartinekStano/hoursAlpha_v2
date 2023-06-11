package sk.posam.hoursalpha.domain;

import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "verfication_token", schema = "public")
@Access(AccessType.FIELD)
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "expiry_date")
    private Timestamp expiryDate;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    public VerificationToken() {
    }

    public VerificationToken(String token, Employee employee) {
        this.token = token;
        this.employee = employee;
    }

    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getToken() {
        return token;
    }

    public Employee getEmployee() {
        return employee;
    }
}
