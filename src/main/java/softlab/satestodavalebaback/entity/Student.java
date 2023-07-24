package softlab.satestodavalebaback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "students")
@SequenceGenerator(name = "studentIdGenerator", sequenceName = "students_id_seq", allocationSize = 1)

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentIdGenerator")
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "id_number",  nullable = false)
    private String idNumber;
    @Column(name = "mail",  nullable = false)
    private String mail;
    @Column(name = "birth_date",  nullable = false)
    private Date birthDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

}
