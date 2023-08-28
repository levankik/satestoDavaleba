package softlab.satestodavalebaback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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
    @NotEmpty(message = "Name is mandatory")
    @Size(max = 10)
    private String name;

    @Column(name = "last_name", nullable = false)
    @NotEmpty(message = "LastName is mandatory")
    @Size (max = 15)
    private String lastName;

    @Column(name = "id_number",  nullable = false)
    @NotEmpty(message = "IdNumber is mandatory")
    @Size (max = 11)
    private String idNumber;

    @Column(name = "mail",  nullable = false)
    @Email(message = "Mail format is not valid")
    private String mail;

    @Column(name = "birth_date",  nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;
}