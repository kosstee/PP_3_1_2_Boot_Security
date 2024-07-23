package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UsersService {

    UserDTO save(User user);

    List<UserDTO> getUsers();

    UserDTO getUserById(Long id);

    UserDTO update(Long id, User user);

    void delete(Long id);
}
