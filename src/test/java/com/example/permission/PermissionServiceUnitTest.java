package com.example.permission;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ExtendWith(MockitoExtension.class)
public class PermissionServiceUnitTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private RoleMapper roleMapper;

    @Mock
    private GroupMapper groupMapper;

    @InjectMocks
    private ServiceImp serviceImp;
    @Test
    public void testAddRole(){
//        arrange

        Role role = new Role();
        role.setName("view");
        role.setId(1L);

        RolePost rolePost = new RolePost();
        rolePost.setName(role.getName());

//        act

        Mockito.when(roleMapper.dtoToRole(rolePost)).thenReturn(role);
        Mockito.when(roleRepository.save(role)).thenReturn(role);

        Role roleTest = serviceImp.createRole(rolePost);

//        assert
        assertEquals(role.getName(), roleTest.getName());
    }

    @Test
    public void testAddGroup(){
//        arrange
        Role role = new Role();
        role.setName("view");
        role.setId(1L);

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        Group group = new Group();
        group.setName("User");
        group.setRoles(roleSet);

        GroupDtoPost groupDtoPost = new GroupDtoPost();
        groupDtoPost.setName(groupDtoPost.getName());
        groupDtoPost.setRoleIds(group.getRoles().stream().map(BaseEntity::getId).collect(Collectors.toSet()));

//        act
        Mockito.when(groupMapper.dtoToGroup(groupDtoPost)).thenReturn(group);
        Mockito.when(groupRepository.save(group)).thenReturn(group);

        Group group1 = serviceImp.createGroup(groupDtoPost);

//        assert
        assertNotNull(group1);
        assertEquals(group1.getName(), group.getName());
        assertEquals(group1.getRoles(), group.getRoles());

    }






}
