package com.example.permission;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GroupRepositoryTests {


    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testFindAll() {
        Role role = new Role();
        role.setName("view");
        role.setId(1L);

        Role role1 = new Role();
        role.setName("delete");
        role.setId(2L);

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        roleSet.add(role1);

        Group group = new Group();
        group.setName("User");
        group.setId(1L);
        group.setRoles(roleSet);
        roleRepository.save(role);
        roleRepository.save(role1);
        groupRepository.save(group);

        List<Group> groups = groupRepository.findAll();

        assertEquals(groups.size(), 1);
    }

    @Test
    public void testFindById(){
        Role role = new Role();
        role.setName("view");
        role.setId(1L);

        Role role1 = new Role();
        role.setName("delete");
        role.setId(2L);

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        roleSet.add(role1);

        Group group = new Group();
        group.setName("User");
        group.setId(1L);
        group.setRoles(roleSet);
        roleRepository.save(role);
        roleRepository.save(role1);
        Group save = groupRepository.save(group);

        Optional<Group> group1 = groupRepository.findById(save.getId());

        assertNotNull(group1.get());
        assertEquals(group1.get().getRoles().size(), 2);
    }


}
