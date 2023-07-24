package softlab.satestodavalebaback.service;

import softlab.satestodavalebaback.entity.Student;
import softlab.satestodavalebaback.entity.Teacher;

import java.util.Date;
import java.util.List;

public interface StudentService {

    Student add(Student student);

    Student update(Student student, int id);

    String delete(int id);

    Student  getStudentById(int id);

    List<Student> getByParams (String name, String lastName, String idNumber, Date birthDate);

}
