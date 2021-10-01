package com.tchokoapps.springboot.ecommerce.training.controllers;

import com.tchokoapps.springboot.ecommerce.training.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserRestController {
    private UserService userService;

    @PostMapping("/users/check-email")
    public boolean checkDuplicateEmail(@Param("email") String email) {
        return userService.isEmailUnique(email);
    }
}
