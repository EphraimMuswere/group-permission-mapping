package com.example.permission;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class GroupMapper {

    private final RoleRepository roleRepository;

    Group dtoToGroup(GroupDtoPost groupDtoPost){
        Group group = new Group();
        group.setName(groupDtoPost.getName());
        Set<Role> roleSet = new HashSet<>();
        final Set<Role> roles = groupDtoPost.getRoleIds().parallelStream()
                .map(roleId -> roleRepository.findById(roleId)
                        .orElseThrow(()->new RuntimeException("Not Found")))
                .collect(Collectors.toSet());
//        for(Long role: groupDtoPost.getRoleIds()){
//            roleSet.add(roleRepository.findById(role).get());
//        }
        group.setRoles(roles);
        return group;

    }
}
