package softlab.satestodavalebaback.service;

import softlab.satestodavalebaback.DTO.TeacherSearchParams;
import softlab.satestodavalebaback.entity.Teacher;

import java.util.List;


public interface TeacherService {
    Teacher add(Teacher teacher);

    Teacher getTeacher(TeacherSearchParams teacherSearchParams);

    Teacher update(int teacherId, Teacher teacher);

    void delete(int teacherId);

    Teacher  getTeacherByTeacherId(int teacherId);

    List<Teacher> getTeachers();

}
