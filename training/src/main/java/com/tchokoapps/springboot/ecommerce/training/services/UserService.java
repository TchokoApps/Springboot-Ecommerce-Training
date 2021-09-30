package com.tchokoapps.springboot.ecommerce.training.services;

import com.tchokoapps.springboot.ecommerce.training.entities.User;
import com.tchokoapps.springboot.ecommerce.training.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {
    UserRepository userRepository;

    public List<User> retrieveAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void save(@NonNull User user) {
        User savedUser = userRepository.save(user);
        log.info("User: {} saved successfully", savedUser);
    }

    public void save(@NonNull List<User> users) {
        users.forEach(this::save);
    }
}
