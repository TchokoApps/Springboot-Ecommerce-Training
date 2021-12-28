package com.tchokoapps.springboot.ecommerce.training.repository;

import com.tchokoapps.springboot.ecommerce.training.entity.Role;
import com.tchokoapps.springboot.ecommerce.training.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUser() {

        User user = new User();
        user.setFirstName("Alain");
        user.setLastName("Tchokonte");

        Role role = new Role();
        role.setName("Admin");

        Role role2 = new Role();
        role2.setName("Manager");

        user.getRoles().addAll(Arrays.asList(role,role2));

        User savedUser = userRepository.save(user);
        log.info("User: {}", savedUser);

        assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void findUserByEmail() {

        String email = "foo@bar.com";
        String firstName = "Foo";
        String lastName = "Bar";

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        User savedUser = userRepository.save(user);

        log.info("SavedUser: {}", savedUser);

        User userByEmail = userRepository.findByEmail(email);
        log.info("UserByEmail: {}", userByEmail);

        assertThat(userByEmail.getFirstName()).isEqualTo(firstName);
        assertThat(userByEmail.getLastName()).isEqualTo(lastName);
        assertThat(userByEmail.getEmail()).isEqualTo(email);

    }

    @Test
    void countById() {
        Long aLong = userRepository.countById(1);
        assertThat(aLong).isNotNull().isGreaterThan(0);
    }
}