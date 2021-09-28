package com.tchokoapps.springboot.ecommerce.training.repositories;

import com.tchokoapps.springboot.ecommerce.training.entities.Role;
import com.tchokoapps.springboot.ecommerce.training.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;

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

        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

    }

}