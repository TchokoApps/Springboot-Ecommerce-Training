package com.tchokoapps.springboot.ecommerce.training.controller;

import com.tchokoapps.springboot.ecommerce.training.entity.Role;
import com.tchokoapps.springboot.ecommerce.training.entity.User;
import com.tchokoapps.springboot.ecommerce.training.exception.UserNotFoundException;
import com.tchokoapps.springboot.ecommerce.training.service.RoleService;
import com.tchokoapps.springboot.ecommerce.training.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("pageTitle", "Create New User");

        return "user-form";
    }

    @PostMapping("/users/save")
    public String save(User user, RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "The user has been saved successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findById(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID=" + id + ")");
            List<Role> listRoles = roleService.retrieveAllRoles();
            model.addAttribute("listRoles", listRoles);
            return "user-form";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }


    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findById(id);
            userService.delete(id);
            redirectAttributes.addFlashAttribute("message", String.format("The user %s, %s has been deleted successfully",
                    user.getFirstName(), user.getLastName()));
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
        return "redirect:/users";
    }


}
