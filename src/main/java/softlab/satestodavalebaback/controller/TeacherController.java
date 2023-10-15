package softlab.satestodavalebaback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import softlab.satestodavalebaback.DTO.SearchParams;
import softlab.satestodavalebaback.entity.Teacher;
import softlab.satestodavalebaback.service.StudentAndTeacherService;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final StudentAndTeacherService<Teacher> studentAndTeacherService;

    @PostMapping("/save")
    public ResponseEntity<?> add(@RequestBody Teacher teacher) {
        studentAndTeacherService.add(teacher);
        var location = UriComponentsBuilder.fromPath("/teachers/" + teacher.getId()).build().toUri();
        return ResponseEntity.created(location).body(teacher);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(studentAndTeacherService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> update(@RequestBody Teacher teacher, @PathVariable int id) {
        return new ResponseEntity<> (studentAndTeacherService.update(teacher, id), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(studentAndTeacherService.getById(id), HttpStatus.OK);
    }

//    @GetMapping("")
//    public Page<?> getAll(SearchParams params, @RequestParam(required = false, defaultValue = "0") int page,
//                                               @RequestParam(required = false, defaultValue = "10") int size) {
//        return studentAndTeacherService.getAll(params, PageRequest.of(page, size));
//    }

    @GetMapping("")
    public List<?> getAll(SearchParams params) {
        return studentAndTeacherService.getAll(params);
    }


}