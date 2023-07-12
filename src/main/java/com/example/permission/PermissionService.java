package com.example.permission;

import java.util.List;

public interface PermissionService {

    Role createRole(RolePost role);

    Group createGroup(GroupDtoPost groupDtoPost);

    Group addRoles(Long id, List<Long> roleIds);
}
