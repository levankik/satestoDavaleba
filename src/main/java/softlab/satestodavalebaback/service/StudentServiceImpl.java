package softlab.satestodavalebaback.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.pagination.SQLServer2005LimitHandler;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import softlab.satestodavalebaback.DTO.StudentSearchParams;
import softlab.satestodavalebaback.entity.Student;
import softlab.satestodavalebaback.entity.Teacher;
import softlab.satestodavalebaback.exeption.NotFoundException;
import softlab.satestodavalebaback.repository.StudentRepository;

import java.security.InvalidParameterException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;


    @Override
    public Student add(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(StudentSearchParams studentSearchParams) {
        String idNumber =  studentSearchParams.getIdNumber();
        return  studentRepository.findByIdNumber(idNumber)
                .orElseThrow(() -> new NotFoundException("Student not found"));
    }

    @Override
    public Student update(Student student ) {
        var foundStudent =  studentRepository.findByIdNumber(student.getIdNumber())
                .orElseThrow(() -> new NotFoundException("Student not found"));;
        foundStudent.setName(student.getName());
        foundStudent.setLastName(student.getLastName());
        foundStudent.setIdNumber(student.getIdNumber());
        foundStudent.setBirthDate(student.getBirthDate());
        return studentRepository.save(foundStudent);
    }

    @Override
    public void delete(int id) {
        Student foundTeacher = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with given id is not found"));
            studentRepository.deleteById(id);
        }


    @Override
    public Student getStudentById(int id) {
        if (id < 1) {
            throw new InvalidParameterException("Is must be positive integer");
        }
        return  studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

    }



}
