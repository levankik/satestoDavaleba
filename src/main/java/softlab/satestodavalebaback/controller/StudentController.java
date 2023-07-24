package softlab.satestodavalebaback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import softlab.satestodavalebaback.entity.Student;
import softlab.satestodavalebaback.service.StudentService;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<?> add(@RequestBody Student student) {
        studentService.add(student);
        var location = UriComponentsBuilder.fromPath("/students/saveStudent/" + student.getId()).build().toUri();
        return ResponseEntity.created(location).body(student);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable int id) {
        return new ResponseEntity<>(studentService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> update (@RequestBody Student student, @PathVariable int id) {
        return new ResponseEntity<> (studentService.update(student, id), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById (@PathVariable Integer id) {
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public List<Student> getByParams(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam (value = "lastName", required = false) String  lastName,
                                     @RequestParam (value = "idNumber", required = false) String idNumber,
                                     @RequestParam (value = "birthDate", required = false) Date birthDate
                                     ) {
        return studentService.getByParams(name, lastName, idNumber, birthDate);
    }
}
