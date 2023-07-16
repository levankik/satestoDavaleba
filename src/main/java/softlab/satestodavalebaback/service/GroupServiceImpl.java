package softlab.satestodavalebaback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softlab.satestodavalebaback.entity.Group;
import softlab.satestodavalebaback.entity.Teacher;
import softlab.satestodavalebaback.exeption.NotFoundException;
import softlab.satestodavalebaback.repository.GroupRepository;
import softlab.satestodavalebaback.repository.TeacherRepository;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public Group add(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group getGroup(int groupNumber) {
        return groupRepository.findByGroupNumber(groupNumber)
                .orElseThrow(() -> new NotFoundException("Group not found"));
    }

    @Override
    public Group update(int groupId, Group group) {
        var foundGroup = getGroupById(groupId);
        foundGroup.setName(group.getName());
        foundGroup.setGroupNumber(group.getGroupNumber());
        return groupRepository.save(foundGroup);
    }

    @Override
    public void delete(int groupId) {
        Group foundGroup = groupRepository.findByGroupId(groupId)
                .orElseThrow(() -> new NotFoundException("Group with given groupId is not found"));
        groupRepository.deleteById(groupId);
    }

    @Override
    public Group getGroupById(int groupId) {
        if (groupId < 1) {
            throw new InvalidParameterException("groupId must be positive integer");
        }
        return  groupRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException("Group not found"));
    }

    @Override
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group assignTeacherToGroup(int groupId, int teacherId) {
        Set<Teacher> teacherSet = null;
        Group group = groupRepository.findByGroupId(groupId)
                .orElseThrow(() -> new NotFoundException("Group is not found"));
        Teacher teacher = teacherRepository.findByTeacherId(teacherId).
                orElseThrow(() -> new NotFoundException("Teacher is not found"));
        teacherSet = group.getAssignedTeachers();
        teacherSet.add(teacher);
        group.setAssignedTeachers(teacherSet);
        return groupRepository.save(group);
    }

}
