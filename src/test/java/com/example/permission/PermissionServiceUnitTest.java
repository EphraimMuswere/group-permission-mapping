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

import java.util.*;
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

    @Test
    public void addRoles(){
//        arrange
        Role role = new Role();
        role.setName("view");
        role.setId(1L);

        Role role1 = new Role();
        role1.setName("edit");
        role1.setId(2L);

        Role role2 = new Role();
        role2.setName("delete");
        role2.setId(3L);

        Set<Optional<Role>> roleSet = new HashSet<>();
        roleSet.add(Optional.of(role1));
        roleSet.add(Optional.of(role2));

        Set<Role> initialRoles = new HashSet<>();
        initialRoles.add(role);

        Long groupId = 1L;
        Long roleId = 1L;

        List<Long> roleIds = new ArrayList<>();
        roleIds.add(2L);
        roleIds.add(3L);

        Group group = new Group();
        group.setName("User");
        group.setRoles(initialRoles);
        group.setId(1L);

//        act
        Mockito.when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));
        Mockito.when(roleRepository.findById(role1.getId())).thenReturn(Optional.of(role1));
        Mockito.when(roleRepository.findById(role2.getId())).thenReturn(Optional.of(role2));
        Mockito.when(groupRepository.save(group)).thenReturn(group);

        Group result = serviceImp.addRoles(groupId, roleIds);

//        assert
        assertNotNull(result);
        assertEquals(result.getRoles(), group.getRoles());
        assertEquals(result.getRoles().size(), group.getRoles().size());
        assertTrue(result.getRoles().contains(role1));
        assertFalse(result.getRoles().isEmpty());
        assertEquals(3, result.getRoles().size());
    }

    @Test(expected = RuntimeException.class)
    public void addRoles_shouldThrowRuntimeExceptionWhenRoleNotFound(){
//        arrange
        Role role = new Role();
        role.setName("view");
        role.setId(1L);

        Role role1 = new Role();
        role1.setName("edit");
        role1.setId(2L);

        Role role2 = new Role();
        role2.setName("delete");
        role2.setId(3L);

        Set<Optional<Role>> roleSet = new HashSet<>();
        roleSet.add(Optional.of(role1));
        roleSet.add(Optional.of(role2));

        Set<Role> initialRoles = new HashSet<>();
        initialRoles.add(role);

        Long groupId = 1L;
        Long roleId = 1L;

        List<Long> roleIds = new ArrayList<>();
        roleIds.add(2L);
        roleIds.add(3L);

        Group group = new Group();
        group.setName("User");
        group.setRoles(initialRoles);
        group.setId(1L);

//        act
        Mockito.when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));
        Mockito.when(roleRepository.findById(role1.getId())).thenReturn(Optional.of(role1));

        Group group1 = serviceImp.addRoles(groupId, roleIds);

//        assert
        assertNotNull(group1);

    }

    @Test
    public void getOneGroupTest(){
//        arrange
        Long groupId = 1L;

        Role role = new Role();
        role.setName("view");
        role.setId(1L);

        Set<Role> initialRoles = new HashSet<>();
        initialRoles.add(role);

        Group group = new Group();
        group.setName("User");
        group.setRoles(initialRoles);
        group.setId(1L);

//        mock behaviour of group repository
        Mockito.when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));

        Group result = serviceImp.getOneGroup(groupId);

        assertNotNull(result);
        assertEquals(result.getId(), group.getId() );


    }





}
