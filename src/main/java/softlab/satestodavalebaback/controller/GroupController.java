package softlab.satestodavalebaback.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import softlab.satestodavalebaback.DTO.SearchParams;
import softlab.satestodavalebaback.entity.Group;
import softlab.satestodavalebaback.service.GroupService;

import java.util.List;

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
    public List<?> getAll(SearchParams params)  {
        return groupService.getAll(params);
    }

    @PostMapping ("/{groupNumber}/{persons}/save/{id}")
    public Group assignToGroup(
            @PathVariable int groupNumber,
            @PathVariable String persons,
            @PathVariable int id
    ) {
        return groupService.assignToGroup(groupNumber, persons, id);
    }

    @PostMapping ("/{groupNumber}/{persons}/remove/{id}")
    public Group removeFromGroup(
            @PathVariable int groupNumber,
            @PathVariable String persons,
            @PathVariable int id
    ) {
        return groupService.removeFromGroup(groupNumber, persons, id);
    }

    @GetMapping("/{groupNumber}/{persons}")
    public ResponseEntity<?> getAssignedPersons (@PathVariable Integer groupNumber,
                                                 @PathVariable String persons) {
        return (persons.equals("teachers")) ?
                new ResponseEntity<>(groupService.getAssignedTeachers(groupNumber), HttpStatus.OK):
                new ResponseEntity<>(groupService.getAssignedStudents(groupNumber), HttpStatus.OK);
    }

}
