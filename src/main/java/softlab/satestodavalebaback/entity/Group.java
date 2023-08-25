package softlab.satestodavalebaback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "groups")
@SequenceGenerator(name = "groupIdGenerator", sequenceName = "groups_id_seq", allocationSize = 1)

public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupIdGenerator")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Name is mandatory")
    @Size (max = 30)
    private String name;

    @Column(name = "group_number", nullable = false)
    @NotNull(message = "GroupNumber is mandatory")
    @Range(min = 1, max = 200) // my faculty group numbers were three-digit
    private int groupNumber;

//    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "group_teacher",
           joinColumns = @JoinColumn(name = "group_id"),
           inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Teacher> assignedTeachers = new HashSet<>();

//    @JsonIgnore
    @OneToMany (mappedBy = "group", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Student> assignedStudents = new HashSet<>();

}
