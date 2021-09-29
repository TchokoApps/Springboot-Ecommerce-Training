package com.tchokoapps.springboot.ecommerce.training.controllers;

import com.tchokoapps.springboot.ecommerce.training.entities.User;
import com.tchokoapps.springboot.ecommerce.training.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class UserController {

    private UserService userService;

    @GetMapping("/users")
    public String retrieveAllUsers(Model model) {
        List<User> users = userService.retrieveAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
