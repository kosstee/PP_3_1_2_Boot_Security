package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminsController {

    @GetMapping("/admin-panel")
    public String showAdminPanel() {
        return "admin/admin-panel";
    }

    @GetMapping("/user-page")
    public String showUserPage() {
        return "admin/user";
    }
}
