package softlab.satestodavalebaback.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import softlab.satestodavalebaback.DTO.SearchParams;
import softlab.satestodavalebaback.entity.Group;

import java.util.Set;

public interface GroupService {

    Group add(Group group);

    Group update(Group group, int id);

    String delete(int id);

    Group  getById(int id);

    Page<?> getAll (SearchParams params, Pageable pageable);

    Group assignToGroup(int groupId, String persons, int id);

    Group removeFromGroup(int groupNumber, String persons, int id);

    Set<?> getAssignedTeachers(Integer groupNumber);

    Set<?> getAssignedStudents(Integer groupNumber);
}
