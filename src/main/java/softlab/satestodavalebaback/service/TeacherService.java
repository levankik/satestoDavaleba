package softlab.satestodavalebaback.service;

import softlab.satestodavalebaback.entity.Teacher;

import java.util.Date;
import java.util.List;


public interface TeacherService {

    Teacher add (Teacher teacher);

    Teacher update (Teacher teacher, int teacherId);

    String delete (int teacherId);

    Teacher  getById (int teacherId);

    List<Teacher> getByParams (String name, String lastName, String idNumber, Date birthDate);

}
