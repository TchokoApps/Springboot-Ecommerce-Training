package com.tchokoapps.springboot.ecommerce.training.controllers;

import com.tchokoapps.springboot.ecommerce.training.entities.Role;
import com.tchokoapps.springboot.ecommerce.training.entities.User;
import com.tchokoapps.springboot.ecommerce.training.services.RoleService;
import com.tchokoapps.springboot.ecommerce.training.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @GetMapping("/users")
    public String retrieveAllUsers(Model model) {
        List<User> users = userService.retrieveAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {

        User user = new User();
        List<Role> listRoles = roleService.retrieveAllRoles();

        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);

        return "user_form";
    }

    @PostMapping("/users/save")
    public String save(User user, RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addFlashAttribute("message","The user has been saved successfully");
        return "redirect:/users";
    }
}
