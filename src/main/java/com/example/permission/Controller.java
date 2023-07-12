package com.example.permission;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class Controller {

    private final PermissionService service;

    @PostMapping("roles")
    public ResponseEntity<Role> save(@RequestBody RolePost role) {
        return new ResponseEntity<>(service.createRole(role), HttpStatus.CREATED);
    }

    @PostMapping("groups")
    public ResponseEntity<Group> create(@RequestBody GroupDtoPost post) {
        return new ResponseEntity<>(service.createGroup(post), HttpStatus.CREATED);
    }
    @PostMapping("assign")
    public ResponseEntity<Group> assign(@RequestParam (value = "groupId") Long id,
                                        @RequestBody List<Long> roleIds) {
        return new ResponseEntity<>(service.addRoles(id, roleIds), HttpStatus.CREATED);
    }
}
