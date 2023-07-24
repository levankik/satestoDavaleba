package softlab.satestodavalebaback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "teachers")
@SequenceGenerator(name = "teacherIdGenerator", sequenceName = "teachers_id_seq", allocationSize = 1)

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacherIdGenerator")
    @Column(name = "id", nullable = false)
    private int teacherId;
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
    @ManyToMany (mappedBy = "assignedTeachers")
    private Set<Group> groupSet = new HashSet<>();
}
