package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UsersService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminsController {

    private final UsersService usersService;
    private final RoleRepository roleRepository;

    @GetMapping("/users")
    public String showAllUsers(ModelMap model) {
        model.addAttribute("users", usersService.getUsers());
        return "admin/users";
    }

    @GetMapping("/user")
    public String showUserInfo(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", usersService.getUserById(id));
        return "admin/user";
    }

    @GetMapping("/new-user")
    public String showCreateUsersForm(Model model) {
        model.addAttribute("allRoles", roleRepository.findAll());
        model.addAttribute("user", new User());
        return "admin/new-user";
    }

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute("user") User user) {
        usersService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit-user")
    public String showEditUsersForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", usersService.getUserById(id));
        return "admin/edit-user";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        usersService.update(id, user);
        return "redirect:/admin/users";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam("id") Long id) {
        usersService.delete(id);
        return "redirect:/admin/users";
    }
}
