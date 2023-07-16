package softlab.satestodavalebaback.service;

import softlab.satestodavalebaback.DTO.StudentSearchParams;
import softlab.satestodavalebaback.entity.Student;

public interface StudentService {

    Student add(Student student);

    Student getStudent(StudentSearchParams studentSearchParams);

    Student update(int idS, Student student);

    void delete(int id);

    Student  getStudentById(int id);


}
