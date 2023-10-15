package softlab.satestodavalebaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import softlab.satestodavalebaback.entity.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer>,
                                           JpaSpecificationExecutor<Student>  {
                                           Optional<Student> findById(int id);
                                           }
