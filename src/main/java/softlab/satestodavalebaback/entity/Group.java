package softlab.satestodavalebaback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
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
    private int groupId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "group_number", nullable = false)
    private int groupNumber;

    @ManyToMany
    @JoinTable(name = "group_teacher",
           joinColumns = @JoinColumn(name = "group_id"),
           inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Teacher> assignedTeachers = new HashSet<>();

    @OneToMany (mappedBy = "group", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Student> assignedStudents = new HashSet<>();

}
