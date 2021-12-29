package com.tchokoapps.springboot.ecommerce.training.repository;

import com.tchokoapps.springboot.ecommerce.training.entity.Role;
import com.tchokoapps.springboot.ecommerce.training.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Optional;

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

        user.getRoles().addAll(Arrays.asList(role, role2));

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

    @Test
    public void disableUser() {
        Optional<User> userOpt = userRepository.findById(1);

        userOpt.ifPresent(user -> assertThat(user.isEnabled()).isFalse());

        userOpt.ifPresent(user -> {
            user.setEnabled(true);
            userRepository.save(user);
        });

        Optional<User> userOpt2 = userRepository.findById(1);

        userOpt2.ifPresent(user -> assertThat(user.isEnabled()).isTrue());

    }

    @Test
    public void listFirstPage() {
        int pageNum = 1;
        int pageSize = 4;
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<User> userPage = userRepository.findAll(pageRequest);
        userPage.getContent().forEach(System.out::println);
        assertThat(userPage.getContent().size()).isEqualTo(4);
    }
}