package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.mapper.UserMapper;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.security.UserPrincipal;
import ru.kata.spring.boot_security.demo.service.UsersService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminsRestController {

    private final UsersService usersService;
    private final RoleRepository roleRepository;

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return usersService.getUsers();
    }

    @GetMapping("/user")
    public UserDTO getAuthorizedUserInfo() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UserMapper.INSTANCE.userToUserDTO(principal.getUser());
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return usersService.getUserById(id);
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @PostMapping("/save-user")
    public UserDTO saveUser(@RequestBody User user) {
        return usersService.save(user);
    }

    @PutMapping("/users/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody User user) {
        return usersService.update(id, user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        usersService.delete(id);
    }
}
