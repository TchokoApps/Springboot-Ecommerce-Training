package com.tchokoapps.springboot.ecommerce.training.controller;

import com.tchokoapps.springboot.ecommerce.training.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController(value = "/users")
public class UserRestController {
    private UserService userService;

    @PostMapping("/check-email")
    public boolean checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email) {
        return userService.isEmailUnique(id, email);
    }
}
