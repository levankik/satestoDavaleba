package softlab.satestodavalebaback.controller;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import softlab.satestodavalebaback.DTO.SearchParams;
import softlab.satestodavalebaback.entity.Student;
import softlab.satestodavalebaback.service.StudentAndTeacherService;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    @Resource(name = "studentServiceImpl")
    private final StudentAndTeacherService<Student> studentAndTeacherService;

    @PostMapping("/save")
    public ResponseEntity<?> add(@RequestBody Student student) {
        studentAndTeacherService.add(student);
        var location = UriComponentsBuilder.fromPath("/students/" + student.getId()).build().toUri();
        return ResponseEntity.created(location).body(student);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable int id) {
        return new ResponseEntity<>(studentAndTeacherService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> update (@RequestBody Student student, @PathVariable int id) {
        return new ResponseEntity<> (studentAndTeacherService.update(student, id), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById (@PathVariable Integer id) {
        return new ResponseEntity<>(studentAndTeacherService.getById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public List<?> getAll(SearchParams params) {return studentAndTeacherService.getAll(params);}


}
