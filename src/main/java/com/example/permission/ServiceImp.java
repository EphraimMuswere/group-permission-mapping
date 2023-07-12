package com.example.permission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


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
}
