package softlab.satestodavalebaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softlab.satestodavalebaback.entity.Teacher;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findByIdNumber(String idNumber);
    Optional<Teacher> findByTeacherId(int teacherId);
}
