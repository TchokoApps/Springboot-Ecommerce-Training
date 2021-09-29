package com.tchokoapps.springboot.ecommerce.training;

import com.github.javafaker.Faker;
import com.tchokoapps.springboot.ecommerce.training.entities.Role;
import com.tchokoapps.springboot.ecommerce.training.entities.User;
import com.tchokoapps.springboot.ecommerce.training.services.RoleService;
import com.tchokoapps.springboot.ecommerce.training.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@SpringBootApplication
public class TrainingApplication implements CommandLineRunner {

    private RoleService roleService;
    private UserService userService;


    public static void main(String[] args) {
        SpringApplication.run(TrainingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        Role adminRole = Role.builder().name("Admin").description("For Admin").build();
        Role publisherRole = Role.builder().name("Pubblisher").description("For Publisher").build();
        Role customerRole = Role.builder().name("Customer").description("For Customer").build();
        List<Role> roles = Arrays.asList(adminRole, publisherRole, customerRole);
        roleService.createRoles(roles);

        for (int i = 0; i < 10; i++) {

            User user = new User();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setEmail(user.getFirstName().replace(" ", "") + "."
                    + user.getLastName().replace(" ", "") + "@" + faker.animal().name() + ".com");
            user.setEnabled(false);
            user.setPhotos(faker.artist().name());
            user.setPassword(faker.beer().name());
            user.setRoles(Collections.singleton(roles.get(faker.random().nextInt(3))));

            userService.createUser(user);
        }
    }
}
