package softlab.satestodavalebaback.service;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import softlab.satestodavalebaback.DTO.SearchParams;
import softlab.satestodavalebaback.entity.Group;
import softlab.satestodavalebaback.entity.Student;
import softlab.satestodavalebaback.entity.Teacher;
import softlab.satestodavalebaback.exeption.NotFoundException;
import softlab.satestodavalebaback.repository.GroupRepository;
import softlab.satestodavalebaback.repository.StudentRepository;
import softlab.satestodavalebaback.repository.TeacherRepository;

import java.security.InvalidParameterException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public Group add(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group update(Group group, int id) {
        var foundGroup = getById(id);
        foundGroup.setName(group.getName());
        foundGroup.setGroupNumber(group.getGroupNumber());
        return groupRepository.save(foundGroup);
    }

    @Override
    public String delete(int id) {
        Group foundGroup = groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Group with given id is not found"));
        groupRepository.delete(foundGroup);
        return "Group delete successfully";
    }

    @Override
    public Group getById(int id) {
        if (id < 1) {
            throw new InvalidParameterException("Is must be positive integer");
        }
        return groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Group not found"));
    }

    @Override
    public Page<Group> getAll(SearchParams params, Pageable pageable) {
        return groupRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (StringUtils.isNotEmpty(params.getName())) {
                predicate = cb.and(predicate, cb.equal(root.get("name"), params.getName()));
            }
            if (params.getGroupNumber() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("groupNumber"), params.getGroupNumber()));
            }
            return predicate;
        }, pageable);
    }

    @Override
    public Group assignToGroup(int groupNumber, String persons, int id) {
        Group group = groupRepository.findByGroupNumber(groupNumber)
                .orElseThrow(() -> new NotFoundException("Group is not found"));
        if (persons.equals("teachers")) {
            Set<Teacher> teacherSet = null;
            Teacher teacher = teacherRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Teacher is not found"));
            teacherSet = group.getAssignedTeachers();
            teacherSet.add(teacher);
            group.setAssignedTeachers(teacherSet);
        } else {
            Set<Student> studentSet = null;
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Student is not found"));
            studentSet = group.getAssignedStudents();
            studentSet.add(student);
            group.setAssignedStudents(studentSet);
        }
        return groupRepository.save(group);
    }

    @Override
    public Group removeFromGroup(int groupNumber, String persons, int id) {
        Group group = groupRepository.findByGroupNumber(groupNumber)
                .orElseThrow(() -> new NotFoundException("Group is not found"));
        if (persons.equals("teachers")) {
            Set<Teacher> teacherSet = null;
            Teacher teacher = teacherRepository.findById(id).
                    orElseThrow(() -> new NotFoundException("Teacher is not found"));
            teacherSet = group.getAssignedTeachers();
            teacherSet.remove(teacher);
            group.setAssignedTeachers(teacherSet);
        } else {
            Set<Student> studentSet = null;
            Student student = studentRepository.findById(id).
                    orElseThrow(() -> new NotFoundException("Student is not found"));
            studentSet = group.getAssignedStudents();
            studentSet.remove(student);
            group.setAssignedStudents(studentSet);
        }
        return groupRepository.save(group);
    }

    @Override
    public Set<Teacher> getAssignedTeachers(Integer groupNumber) {
        Set<Teacher> teacherSet = null;
        Group group = groupRepository.findByGroupNumber(groupNumber)
                .orElseThrow(() -> new NotFoundException("Group is not found"));
        teacherSet = group.getAssignedTeachers();
        System.out.println("TeacherSetPrint" + teacherSet);
        return teacherSet;
    }

    @Override
    public Set<Student> getAssignedStudents(Integer groupNumber) {
        Set<Student> studentSet = null;
        Group group = groupRepository.findByGroupNumber(groupNumber)
                .orElseThrow(() -> new NotFoundException("Group is not found"));
        studentSet = group.getAssignedStudents();
        return studentSet;
    }
}