package sk.posam.hoursalpha.domain;

import javax.persistence.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee", schema = "public")
@Access(AccessType.FIELD)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name",
            nullable = false)
    private String firstName;

    @Column(name = "last_name",
            nullable = false)
    private String lastName;

    @Column(name = "email",
            nullable = false)
    private String email;

    @Column(name = "password",
            nullable = false)
    private String password;

    @Column(name = "phone_number",
            nullable = false)
    private String phoneNumber;

    @Column(name = "status")
    private boolean statusOfProfile;

    @OneToMany(mappedBy = "employee")
    private List<DayRecord> listOfYearRecord;

    public Employee(String firstName, String lastName, String email, String password, String phoneNumber, boolean statusOfProfile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.statusOfProfile = statusOfProfile;
        this.listOfYearRecord = new ArrayList<>();
    }

    public Employee() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isStatusOfProfile() {
        return statusOfProfile;
    }

    public void setStatusOfProfile(boolean statusOfProfile) {
        this.statusOfProfile = statusOfProfile;
    }

    public List<DayRecord> getListOfYearRecord() {
        return listOfYearRecord;
    }
}
