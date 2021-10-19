package com.tchokoapps.springboot.ecommerce.training.service;

import com.tchokoapps.springboot.ecommerce.training.entity.User;
import com.tchokoapps.springboot.ecommerce.training.exception.UserNotFoundException;
import com.tchokoapps.springboot.ecommerce.training.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> retrieveAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void save(@NonNull User user) {

        boolean isUpdatingUser = user.getId() != null;
        System.out.println(user);

        if (isUpdatingUser) {
            if (StringUtils.isEmpty(user.getPassword())) {
                userRepository.findById(user.getId()).ifPresent(user1 -> user.setPassword(user1.getPassword()));
            } else {
                encodeUserPassword(user);
            }
        } else {
            encodeUserPassword(user);
        }
        User savedUser = userRepository.save(user);
        log.info("User: {} saved successfully", savedUser);
    }

    private void encodeUserPassword(User user) {
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
    }

    public boolean isEmailUnique(Integer id, String email) {
        User userByEmail = userRepository.findByEmail(email);
        log.info("User found: {}", userByEmail);
        if (id == null) {
            return userByEmail == null;
        } else {
            return userByEmail.getId().equals(id);
        }
    }

    public User findById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(String.format("User with id=%s cannot be found", id)));
    }

}
