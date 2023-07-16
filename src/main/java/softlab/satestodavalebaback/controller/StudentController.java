package softlab.satestodavalebaback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import softlab.satestodavalebaback.DTO.StudentSearchParams;
import softlab.satestodavalebaback.entity.Group;
import softlab.satestodavalebaback.entity.Student;
import softlab.satestodavalebaback.entity.Teacher;
import softlab.satestodavalebaback.service.StudentService;

import java.util.List;


@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping()
    public ResponseEntity<Student> add(@RequestBody Student student) {
        studentService.add(student);
        var location = UriComponentsBuilder.fromPath("/students/" + student.getId()).build().toUri();
        return ResponseEntity.created(location).body(student);
    }

    @GetMapping()
    public Student getStudent(@RequestBody StudentSearchParams studentSearchParams) {
        return studentService.getStudent(studentSearchParams);
    }

    @PutMapping("/{id}")
    public Student update(@RequestBody Student student, @PathVariable int id) {
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable int id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
