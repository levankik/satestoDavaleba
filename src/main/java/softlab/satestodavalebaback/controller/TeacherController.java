package softlab.satestodavalebaback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import softlab.satestodavalebaback.entity.Teacher;
import softlab.satestodavalebaback.service.TeacherService;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/save")
    public ResponseEntity<?> add(@RequestBody Teacher teacher) {
        teacherService.add(teacher);
        var location = UriComponentsBuilder.fromPath("/teachers/" + teacher.getTeacherId()).build().toUri();
        return ResponseEntity.created(location).body(teacher);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(teacherService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> update(@RequestBody Teacher teacher, @PathVariable int id) {
        return new ResponseEntity<> (teacherService.update(teacher, id), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(teacherService.getById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public List<?> getByParams(@RequestParam (value = "name", required = false) String name,
                                     @RequestParam (value = "lastName", required = false) String  lastName,
                                     @RequestParam (value = "idNumber", required = false) String idNumber,
                                     @RequestParam (value = "birthDate", required = false) Date birthDate
                                     ) {
        return teacherService.getByParams(name, lastName, idNumber, birthDate);
    }

}
