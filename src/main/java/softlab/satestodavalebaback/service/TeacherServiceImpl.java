package softlab.satestodavalebaback.service;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import softlab.satestodavalebaback.DTO.SearchParams;
import softlab.satestodavalebaback.entity.Teacher;
import softlab.satestodavalebaback.exeption.NotFoundException;
import softlab.satestodavalebaback.repository.TeacherRepository;

import java.security.InvalidParameterException;

@Primary
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements StudentAndTeacherService<Teacher> {
    private final TeacherRepository teacherRepository;

    @Override
    public Teacher add(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher update(Teacher teacher, int id) {
        var foundTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher not found"));
        foundTeacher.setName(teacher.getName());
        foundTeacher.setLastName(teacher.getLastName());
        foundTeacher.setIdNumber(teacher.getIdNumber());
        foundTeacher.setMail(teacher.getMail());
        foundTeacher.setBirthDate(teacher.getBirthDate());
        return teacherRepository.save(foundTeacher);
    }

    @Override
    public String delete(int id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher with given id is not found"));
        teacherRepository.delete(teacher);
        return "Teacher delete successfully";
    }

    @Override
    public Teacher getById(int id) {
        if (id < 1) {
            throw new InvalidParameterException("Teacher id  must be positive integer");
        }
        return teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher not found"));
    }

    @Override
    public Page<Teacher> getAll(SearchParams params, Pageable pageable) {
        return teacherRepository.findAll((root, query, cb) -> {
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
            return predicate;
        }, pageable);
    }
}

