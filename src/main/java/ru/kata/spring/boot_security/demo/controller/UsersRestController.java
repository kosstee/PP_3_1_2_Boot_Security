package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.mapper.UserMapper;
import ru.kata.spring.boot_security.demo.security.UserPrincipal;

@RestController
@RequestMapping("/api/v1/users")
public class UsersRestController {

    @GetMapping("/user")
    public UserDTO getAuthorizedUserInfo() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UserMapper.INSTANCE.userToUserDTO(principal.getUser());
    }
}
