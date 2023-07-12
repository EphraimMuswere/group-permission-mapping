package com.example.permission;

public interface PermissionService {

    Role createRole(RolePost role);

    Group createGroup(GroupDtoPost groupDtoPost);
}
