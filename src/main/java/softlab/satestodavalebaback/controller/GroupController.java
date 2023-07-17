package softlab.satestodavalebaback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import softlab.satestodavalebaback.entity.Group;
import softlab.satestodavalebaback.service.GroupService;

import java.util.List;


@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping()
    public ResponseEntity<Group> add(@RequestBody Group group) {
        groupService.add(group);
        var location = UriComponentsBuilder.fromPath("/groups/" + group.getGroupId()).build().toUri();
        return ResponseEntity.created(location).body(group);
    }

    @GetMapping("/{groupNumber}")
    public Group getGroup(@PathVariable int groupNumber) {
        return groupService.getGroup(groupNumber);
    }

    @PutMapping("/{groupId}")
    public Group update(@RequestBody Group group, @PathVariable int groupId) {
        return groupService.update(groupId, group);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Group> delete(@PathVariable int groupId) {
        groupService.delete(groupId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public List<Group> getGroups() {
        return groupService.getGroups();
    }

    @PutMapping ("/{groupId}/group/{teacherId}")
    public Group assignTeacherToGroup(
            @PathVariable int groupId,
            @PathVariable int teacherId
    ) {
        return groupService.assignTeacherToGroup(groupId, teacherId);
    }

    @PutMapping ("/{groupId}/group/{teacherId}/remove")
    public Group removeTeacherFromGroup(
            @PathVariable int groupId,
            @PathVariable int teacherId
    ) {
        return groupService.removeTeacherFromGroup(groupId, teacherId);
    }

    @PutMapping ("/{groupId}/group/student/{id}")
    public Group assignStudentToGroup(
            @PathVariable int groupId,
            @PathVariable int id
    ) {
        return groupService.assignStudentToGroup(groupId, id);
    }

    @PutMapping ("/{groupId}/group/student/{id}/remove")
    public Group removeStudentFromGroup(
            @PathVariable int groupId,
            @PathVariable int id
    ) {
        return groupService.removeStudentFromGroup(groupId, id);
    }



}
