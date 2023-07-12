package com.example.permission;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/audit")
public class Controller {

    private final PermissionService service;

    @PostMapping("save")
    public ResponseEntity<Role> save(@RequestBody RolePost role) {
        return new ResponseEntity<>(service.createRole(role), HttpStatus.CREATED);
    }

    @PostMapping("create")
    public ResponseEntity<Group> create(@RequestBody GroupDtoPost post) {
        return new ResponseEntity<>(service.createGroup(post), HttpStatus.CREATED);
    }
}
