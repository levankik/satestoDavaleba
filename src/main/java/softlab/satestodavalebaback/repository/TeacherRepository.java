package softlab.satestodavalebaback.repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import softlab.satestodavalebaback.entity.Teacher;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>,
                                           JpaSpecificationExecutor<Teacher> {
                                           Optional<Teacher> findById(int id);
                                           }
