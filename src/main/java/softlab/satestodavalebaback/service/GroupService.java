package softlab.satestodavalebaback.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import softlab.satestodavalebaback.DTO.SearchParams;
import softlab.satestodavalebaback.entity.Group;

public interface GroupService {

    Group add(Group group);

    Group update(Group group, int id);

    String delete(int id);

    Group  getById(int id);

    Page<?> getAll (SearchParams params, Pageable pageable);

    Group assignTeacher(int groupId, int id);

    Group removeTeacher(int groupId, int id);

    Group assignStudent(int groupId, int id);

    Group removeStudent(int groupId, int id);
}
