package softlab.satestodavalebaback.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import softlab.satestodavalebaback.entity.Student;
import softlab.satestodavalebaback.exeption.NotFoundException;
import softlab.satestodavalebaback.repository.StudentRepository;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Student add(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update (Student student, int id) {
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
    public String delete (int id) {
        Student foundStudent = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with given id is not found"));
            studentRepository.deleteById(id);
        return "Student delete successfully";
        }


    @Override
    public Student getStudentById(int id) {
        if (id < 1) {
            throw new InvalidParameterException("Is must be positive integer");
        }
        return  studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

    }

    @Override
    public List<Student> getByParams(String name, String lastName, String idNumber, Date birthDate) {

        if (name == null && lastName == null && idNumber == null && birthDate == null ) {
            return studentRepository.findAll();
        } else {
            var searchResult = studentRepository.findAll().stream()
                    .filter(s -> (s.getName() == StringUtils.deleteWhitespace(name) ||
                            s.getLastName() == StringUtils.deleteWhitespace(lastName) ||
                            s.getIdNumber() == StringUtils.deleteWhitespace(idNumber) ||
                            s.getBirthDate() == birthDate))
                    .collect(Collectors.toList());
            return searchResult;
        }
    }


}
