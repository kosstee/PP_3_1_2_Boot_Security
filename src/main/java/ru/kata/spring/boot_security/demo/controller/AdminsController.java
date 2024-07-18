package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.security.UserPrincipal;
import ru.kata.spring.boot_security.demo.service.UsersService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminsController {

    private final UsersService usersService;
    private final RoleRepository roleRepository;

    @GetMapping("/admin-panel")
    public String showAdminPanel(Model model) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("authorizedUser", principal.getUser());
        model.addAttribute("users", usersService.getUsers());
        model.addAttribute("allRoles", roleRepository.findAll());
        model.addAttribute("user", new User());
        return "admin/admin-panel";
    }

    @GetMapping("/user")
    public String showUserInfo(Model model) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("authorizedUser", principal.getUser());
        return "admin/user";
    }

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute("user") User user) {
        usersService.save(user);
        return "redirect:/admin/admin-panel";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        usersService.update(id, user);
        return "redirect:/admin/admin-panel";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam("id") Long id) {
        usersService.delete(id);
        return "redirect:/admin/admin-panel";
    }
}
