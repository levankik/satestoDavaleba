package softlab.satestodavalebaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import softlab.satestodavalebaback.entity.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer>,
                                           JpaSpecificationExecutor<Student>,
                                           PagingAndSortingRepository<Student, Integer> {
                                           Optional<Student> findById(int id);
                                           }
