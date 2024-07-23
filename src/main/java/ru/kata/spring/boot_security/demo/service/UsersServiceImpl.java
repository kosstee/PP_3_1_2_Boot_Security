package ru.kata.spring.boot_security.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.mapper.UserMapper;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDTO save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserMapper.INSTANCE.userToUserDTO(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> getUsers() {
        return UserMapper.INSTANCE.usersToUserDTOs(userRepository.findAllWithRoles());
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.INSTANCE.userToUserDTO(user);
    }

    @Transactional
    @Override
    public UserDTO update(Long id, User user) {
        userRepository.findById(id).ifPresent(existingUser -> {
            if (Objects.isNull(user.getPassword()) || user.getPassword().isBlank()) {
                user.setPassword(existingUser.getPassword());
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        });

        return UserMapper.INSTANCE.userToUserDTO(userRepository.save(user));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
