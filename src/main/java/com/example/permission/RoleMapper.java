package com.example.permission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RoleMapper {

    Role dtoToRole(RolePost rolePost){
        Role role = new Role();
        role.setName(rolePost.getName());
        return role;
    }
}
