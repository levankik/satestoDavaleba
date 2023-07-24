package softlab.satestodavalebaback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softlab.satestodavalebaback.entity.Teacher;
import softlab.satestodavalebaback.exeption.NotFoundException;
import softlab.satestodavalebaback.repository.TeacherRepository;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
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
    public String delete(int teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new NotFoundException("Teacher with given id is not found"));
        teacherRepository.delete(teacher);
        return "Teacher delete successfully";
    }

    @Override
    public Teacher getById(int teacherId) {
        if (teacherId < 1) {
            throw new InvalidParameterException("teacherId must be positive integer");
        }
        return  teacherRepository.findById(teacherId)
                .orElseThrow(() -> new NotFoundException("Teacher not found"));
    }


    @Override
    public List<Teacher> getByParams(String name, String lastName, String idNumber, Date birthDate) {
//        System.out.println(name);
        var searchResult = teacherRepository.findAll();
        System.out.println(searchResult); // here all right
        searchResult.stream()
                .filter(t -> t.getName().equals(name) ||
                             t.getLastName().equals(lastName) ||
                             t.getIdNumber().equals(idNumber) ||
                             t.getBirthDate().equals(birthDate))
                .collect(Collectors.toList());
        System.out.println(searchResult);
        return searchResult;
    }


}


