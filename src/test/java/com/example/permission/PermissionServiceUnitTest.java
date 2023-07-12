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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ExtendWith(MockitoExtension.class)
public class PermissionServiceUnitTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private ServiceImp serviceImp;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddRole(){
        Role role = new Role();
        role.setName("view");
        role.setId(1L);

        Mockito.when(serviceImp.createRole(role)).thenReturn(role);
        Mockito.when(roleRepository.save(role).getId()).thenReturn(role.getId());

        Role roleTest = serviceImp.createRole(role);

        assertEquals(role.getId(), roleTest.getId());
    }






}
