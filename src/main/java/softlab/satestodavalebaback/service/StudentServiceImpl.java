package softlab.satestodavalebaback.service;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import softlab.satestodavalebaback.DTO.SearchParams;
import softlab.satestodavalebaback.entity.Group;
import softlab.satestodavalebaback.entity.Student;
import softlab.satestodavalebaback.exeption.NotFoundException;
import softlab.satestodavalebaback.repository.StudentRepository;

import java.security.InvalidParameterException;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentAndTeacherService<Student> {
    private final StudentRepository studentRepository;

    @Override
    public Student add(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student, int id) {
        var foundStudent = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher not found"));
        foundStudent.setName(student.getName());
        foundStudent.setLastName(student.getLastName());
        foundStudent.setIdNumber(student.getIdNumber());
        foundStudent.setMail(student.getMail());
        foundStudent.setBirthDate(student.getBirthDate());
        return studentRepository.save(foundStudent);
    }

    @Override
    public String delete(int id) {
        Student foundStudent = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with given id is not found"));
        studentRepository.delete(foundStudent);
        return "Student delete successfully";
    }

    @Override
    public Student getById(int id) {
        if (id < 1) {
            throw new InvalidParameterException("Is must be positive integer");
        }
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    @Override
    public Page<Student> getAll(SearchParams params, Pageable pageable) {
        return studentRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (StringUtils.isNotEmpty(params.getName())) {
                predicate = cb.and(predicate, cb.equal(root.get("name"), params.getName()));
            }
            if (StringUtils.isNotEmpty(params.getLastName())) {
                predicate = cb.and(predicate, cb.equal(root.get("lastName"), params.getLastName()));
            }
            if (StringUtils.isNotEmpty(params.getIdNumber())) {
                predicate = cb.and(predicate, cb.equal(root.get("idNumber"), params.getIdNumber()));
            }
            if (params.getBirthDate() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("birthDate"), params.getBirthDate()));
            }
            if (params.getGroupNumber() != null) {
                Join<Student, Group> group = root.join("group", JoinType.LEFT);
                predicate = cb.and(predicate, cb.equal(root.get("group"), params.getGroupNumber()));
            }
            return predicate;
        }, pageable);
    }


}
