package softlab.satestodavalebaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import softlab.satestodavalebaback.entity.Teacher;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>,
                                           JpaSpecificationExecutor<Teacher>,
                                           PagingAndSortingRepository<Teacher, Integer> {
                                           Optional<Teacher> findById(int id);
                                           }
