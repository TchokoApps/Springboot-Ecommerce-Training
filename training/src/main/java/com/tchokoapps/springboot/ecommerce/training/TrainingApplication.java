package com.tchokoapps.springboot.ecommerce.training;

import com.github.javafaker.Faker;
import com.tchokoapps.springboot.ecommerce.training.entity.Role;
import com.tchokoapps.springboot.ecommerce.training.entity.User;
import com.tchokoapps.springboot.ecommerce.training.repository.RoleRepository;
import com.tchokoapps.springboot.ecommerce.training.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@SpringBootApplication
public class TrainingApplication implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(TrainingApplication.class, args);
    }

    @Override
    public void run(String... args) {

        Faker faker = new Faker();

        Role adminRole = Role
                .builder()
                .name("Admin")
                .description("Admin")
                .build();

        Role publisherRole = Role
                .builder()
                .name("Pubblisher")
                .description("Pubblisher")
                .build();

        Role customerRole = Role
                .builder()
                .name("Customer")
                .description("Customer")
                .build();

        Role roleSalesperson = Role
                .builder()
                .name("Salesperson")
                .description("Salesperson")
                .build();

        Role roleEditor = Role
                .builder()
                .name("Editor")
                .description("Editor")
                .build();

        Role roleShipper = Role
                .builder()
                .name("Shipper")
                .description("Shipper")
                .build();

        Role roleAssistant = Role
                .builder()
                .name("Assistant")
                .description("Assistant")
                .build();

        List<Role> roles = Arrays.asList(adminRole, publisherRole, customerRole, roleSalesperson, roleEditor, roleShipper, roleAssistant);

        roleRepository.saveAll(roles);

        for (int i = 0; i < 10; i++) {

            User user = new User();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setEmail(user.getFirstName().replace(" ", "") + "."
                    + user.getLastName().replace(" ", "") + "@" + faker.animal().name() + ".com");
            user.setEnabled(false);
            user.setPhotos(faker.artist().name());
            user.setPassword(faker.beer().name());
            Set<Role> roleSet = new HashSet<>(Arrays.asList(roles.get(faker.random().nextInt(7)), roles.get(faker.random().nextInt(7))));
            user.setRoles(roleSet);

            userRepository.save(user);
        }
    }
}
