package softlab.satestodavalebaback.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import softlab.satestodavalebaback.DTO.SearchParams;
import softlab.satestodavalebaback.entity.Group;
import softlab.satestodavalebaback.service.GroupService;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/save")
    public ResponseEntity<?> add(@Valid @RequestBody Group group) {
        groupService.add(group);
        var location = UriComponentsBuilder.fromPath("/groups/" + group.getId()).build().toUri();
        return ResponseEntity.created(location).body(group);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<> (groupService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> update(@RequestBody Group group, @PathVariable int id) {
        return new ResponseEntity<> (groupService.update(group, id), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById (@PathVariable Integer id) {
        return new ResponseEntity<>(groupService.getById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public Page<?> getAll(SearchParams params, @RequestParam(required = false, defaultValue = "0") int page,
                          @RequestParam(required = false, defaultValue = "10") int size) {
        return groupService.getAll(params, PageRequest.of(page, size));
    }

    @PostMapping ("/{groupNumber}/teachers/save/{id}")
    public Group assignTeacher(
            @PathVariable int groupNumber,
            @PathVariable int id
    ) {
        return groupService.assignTeacher(groupNumber, id);
    }

    @GetMapping("/{groupNumber}/teachers")
    public ResponseEntity<?> getAssignedTeachers (@PathVariable Integer groupNumber) {
        return new ResponseEntity<>(groupService.getAssignedTeachers(groupNumber), HttpStatus.OK);
    }

    @PostMapping ("/{groupNumber}/teachers/remove/{id}")
    public Group removeTeacher(
            @PathVariable int groupNumber,
            @PathVariable int id
    ) {
        return groupService.removeTeacher(groupNumber, id);
    }

//    (API_URL + destination  + "/remove/" + id)

    @PostMapping ("/{groupId}/save_student/{id}")
    public Group assignStudent(
            @PathVariable int groupId,
            @PathVariable int id
    ) {
        return groupService.assignStudent(groupId, id);
    }

    @GetMapping("/{groupNumber}/get_assigned_students")
    public ResponseEntity<?> getAssignedStudents (@PathVariable Integer groupNumber) {
        return new ResponseEntity<>(groupService.getAssignedStudents(groupNumber), HttpStatus.OK);
    }

    @PostMapping ("/{groupId}/remove_student/{id}")
    public Group removeStudent(
            @PathVariable int groupId,
            @PathVariable int id
    ) {
        return groupService.removeStudent(groupId, id);
    }



}
