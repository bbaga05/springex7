package ru.itmentor.spring.boot_security.demo.сontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;


    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.findAll());
        return "new";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "roleIds", required = false) List<Long> roleIds) {
        if (roleIds != null) {
            Set<Role> roles = roleIds.stream()
                    .map(roleService::findById)
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("allRoles", roleService.findAll());
        return "edit";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable long id,
                             @ModelAttribute("user") User user,
                             @RequestParam(value = "roleIds", required = false) List<Long> roleIds) {
        user.setId(id);
        if (roleIds != null) {
            Set<Role> roles = roleIds.stream()
                    .map(roleService::findById)
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }
        userService.update(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}