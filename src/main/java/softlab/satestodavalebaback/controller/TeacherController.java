package softlab.satestodavalebaback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import softlab.satestodavalebaback.DTO.TeacherSearchParams;
import softlab.satestodavalebaback.entity.Teacher;
import softlab.satestodavalebaback.service.TeacherService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping()
    public ResponseEntity<Teacher> add(@RequestBody Teacher teacher) {
        teacherService.add(teacher);
        var location = UriComponentsBuilder.fromPath("/teachers/" + teacher.getTeacherId()).build().toUri();
        return ResponseEntity.created(location).body(teacher);
    }

    @GetMapping("/bysearchparams")
    public Teacher getTeacher(@RequestBody TeacherSearchParams teacherSearchParams) {
        return teacherService.getTeacher(teacherSearchParams);
    }

    @PutMapping()
    public Teacher update(@RequestBody Teacher teacher) {
        return teacherService.update(teacher);
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Teacher> delete(@PathVariable int teacherId) {
        teacherService.delete(teacherId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

}
