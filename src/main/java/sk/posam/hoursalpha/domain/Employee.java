package sk.posam.hoursalpha.domain;

import javax.persistence.*;

@Entity
@Table(name = "employee", schema = "public")
@Access(AccessType.FIELD)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
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
}
