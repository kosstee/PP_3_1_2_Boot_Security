package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UsersService;

import java.util.Objects;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/user")
    public String showUserInfo(@RequestParam("id") Long id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!Objects.equals(user.getId(), id)) {
            return "/error/403";
        }

        model.addAttribute("user", usersService.getUserById(id));
        return "user/user";
    }
}
