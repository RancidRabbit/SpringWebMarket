package ru.gb.Ex.webApp.services;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.gb.Ex.webApp.entities.Role;
import ru.gb.Ex.webApp.repositories.UserRepo;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    /*@Test
    public void findByUsernameTest() {

        User user = new User();
        user.setUsername("Pete");
        user.setEmail("pete@gmail.com");

        Mockito.doReturn(Optional.of(user))
                .when(userRepo)
                .findByUsername("Pete");

        User userFromBD = userService.findByUsername("Pete").get();

        Assertions.assertNotNull(userFromBD);
        Assertions.assertEquals("pete@gmail.com", userFromBD.getEmail());

        Mockito.verify(userRepo, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("Pete"));

    }*/

    @Test
    public void loadUserByUsernameTest() {


        Assertions.assertNotNull(userService.loadUserByUsername("Наташа"));

        UserDetails userDetails = userService.loadUserByUsername("Наташа");

        Assertions.assertEquals("$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i", userDetails.getPassword());

        Assertions.assertEquals("ROLE_USER", userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()).get(0));

    }

    @Test
    public void loadUserByUsernameTest2() {

        Assertions.assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("Bob"));

    }

    @SneakyThrows
    @Test
    public void mapRolesToAuthoritiesTest() {

        Role role = new Role();
        role.setName("Test");

        Method method = UserService.class.getDeclaredMethod("mapRolesToAuthorities", Collection.class);
        method.setAccessible(true);

        Assertions.assertEquals(((List<SimpleGrantedAuthority>) method.invoke(userService, Collections.singletonList(role))).size(), 1);
    }


    @SneakyThrows
    @Test
    public void mapRolesToAuthoritiesTest1() {

        Role role = new Role();
        role.setName("Test");

        Role role1 = new Role();
        role1.setName("Admin");

        Method method = UserService.class.getDeclaredMethod("mapRolesToAuthorities", Collection.class);
        method.setAccessible(true);

        Assertions.assertEquals(((List<SimpleGrantedAuthority>) method.invoke(userService, Arrays.asList(role, role1))).size(), 2);
    }


}
