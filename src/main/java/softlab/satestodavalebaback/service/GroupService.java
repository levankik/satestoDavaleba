package softlab.satestodavalebaback.service;

import softlab.satestodavalebaback.entity.Group;

import java.util.List;

public interface GroupService {

    Group add(Group group);

    Group getGroup(int groupNumber);

    Group update(int groupId, Group group);

    void delete(int groupId);

    Group  getGroupById(int groupId);

    List<Group> getGroups();

    Group assignTeacherToGroup(int groupId, int teacherId);

    Group removeTeacherFromGroup(int groupId, int teacherId);

    Group assignStudentToGroup(int groupId, int id);

    Group removeStudentFromGroup(int groupId, int id);
}
