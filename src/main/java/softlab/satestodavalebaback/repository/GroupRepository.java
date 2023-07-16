package softlab.satestodavalebaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softlab.satestodavalebaback.entity.Group;
import softlab.satestodavalebaback.entity.Student;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    Optional<Group> findByGroupNumber(int groupNumber);
    Optional<Group> findByGroupId(int groupId);
}
