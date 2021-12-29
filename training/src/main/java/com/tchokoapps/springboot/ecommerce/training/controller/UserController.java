package com.tchokoapps.springboot.ecommerce.training.controller;

import com.tchokoapps.springboot.ecommerce.training.entity.Role;
import com.tchokoapps.springboot.ecommerce.training.entity.User;
import com.tchokoapps.springboot.ecommerce.training.exception.UserNotFoundException;
import com.tchokoapps.springboot.ecommerce.training.service.RoleService;
import com.tchokoapps.springboot.ecommerce.training.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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

    public static final int FIRST_PAGE = 1;
    public static final int PAGE_SIZE = 4;
    private UserService userService;
    private RoleService roleService;

    @GetMapping("/users")
    public String showFirstPage(Model model) {
        return showUsersByPage(FIRST_PAGE, model);
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

    @GetMapping("/users/{id}/enabled/{status}")
    public String updateEnabledStatusUser(@PathVariable(name = "id") Integer id, @PathVariable(name = "status") boolean enabled,
                                          RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findById(id);
            user.setEnabled(enabled);
            userService.save(user);
            String enabledString = enabled ? "ENABLED" : "DISABLED";
            redirectAttributes.addFlashAttribute("message", String.format("The status of user %s, %s has been %s ",
                    user.getFirstName(), user.getLastName(), enabledString));
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
        return "redirect:/users";
    }

    @GetMapping("/users/page/{pageNum}")
    public String showUsersByPage(@PathVariable(name = "pageNum") int pageNum, Model model) {
        Page<User> page = userService.findByPage(pageNum, PAGE_SIZE);
        List<User> users = page.getContent();

        long startCount = (long) (pageNum - 1) * PAGE_SIZE + 1;
        long endCount = startCount + PAGE_SIZE - 1;
        if (endCount > page.getTotalElements()) {
            endCount = page.getTotalElements();
        }

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("startCount", startCount);
        model.addAttribute("endCount", endCount);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("users", users);

        System.out.println(model);

        return "users";
    }


}
