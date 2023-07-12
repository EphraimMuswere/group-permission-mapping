package com.example.permission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Transactional
@RequiredArgsConstructor
@Service
public class ServiceImp implements PermissionService {

    private final RoleMapper roleMapper;
    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;
    private final RoleRepository roleRepository;

    @Override
    public Role createRole(RolePost role) {
        return roleRepository.save(roleMapper.dtoToRole(role));

    }

    @Override
    public Group createGroup(GroupDtoPost groupDtoPost) {
        return groupRepository.save(groupMapper.dtoToGroup(groupDtoPost));
    }

    @Override
    public Group addRoles(Long id, List<Long> roleIds) {
        Group group = groupRepository.findById(id).orElseThrow(()->new RuntimeException("Group not found: " + id));

        Set<Role> roleSet = new HashSet<>();

        for(Long role: roleIds){
            roleSet.add(roleRepository.findById(role).orElseThrow(()->new RuntimeException("Role not found: "+ role)));
        }

        group.getRoles().addAll(roleSet);
        return groupRepository.save(group);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group getOneGroup(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(()->new RuntimeException("Group not found with id: "+groupId));
    }
}
