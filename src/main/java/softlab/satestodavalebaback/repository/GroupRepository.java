package softlab.satestodavalebaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import softlab.satestodavalebaback.entity.Group;
import softlab.satestodavalebaback.entity.Student;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer>,
                                         JpaSpecificationExecutor<Group>,
                                         PagingAndSortingRepository<Group, Integer> {
//    Optional<Group> findByGroupNumber(int groupNumber);
                                         Optional<Group> findById(int id);
                                         }
