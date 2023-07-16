package softlab.satestodavalebaback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softlab.satestodavalebaback.DTO.TeacherSearchParams;
import softlab.satestodavalebaback.entity.Teacher;
import softlab.satestodavalebaback.exeption.NotFoundException;
import softlab.satestodavalebaback.repository.TeacherRepository;

import java.security.InvalidParameterException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public Teacher add(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacher(TeacherSearchParams teacherSearchParams) {
        String idNumber =  teacherSearchParams.getIdNumber();
        return  teacherRepository.findByIdNumber(idNumber)
                .orElseThrow(() -> new NotFoundException("Teacher not found"));
    }

    @Override
    public Teacher update(int teacherId, Teacher teacher ) {
        var foundTeacher = getTeacherByTeacherId(teacherId);
        foundTeacher.setName(teacher.getName());
        foundTeacher.setLastName(teacher.getLastName());
        foundTeacher.setIdNumber(teacher.getIdNumber());
        foundTeacher.setBirthDate(teacher.getBirthDate());
        return teacherRepository.save(foundTeacher);
    }

    @Override
    public void delete(int teacherId) {
        Teacher foundTeacher = teacherRepository.findByTeacherId(teacherId)
                .orElseThrow(() -> new NotFoundException("Teacher with given id is not found"));
        teacherRepository.deleteById(teacherId);
    }

    @Override
    public Teacher getTeacherByTeacherId(int teacherID) {
        if (teacherID < 1) {
            throw new InvalidParameterException("teacherId must be positive integer");
        }
        return  teacherRepository.findByTeacherId(teacherID)
                .orElseThrow(() -> new NotFoundException("Teacher not found"));
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }
}
