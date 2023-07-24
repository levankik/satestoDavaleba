package softlab.satestodavalebaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softlab.satestodavalebaback.entity.Student;
import softlab.satestodavalebaback.entity.Teacher;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findById(int id);
}
