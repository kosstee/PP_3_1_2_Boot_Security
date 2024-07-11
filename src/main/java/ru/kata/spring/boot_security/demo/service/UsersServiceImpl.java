package ru.kata.spring.boot_security.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsers() {
        return userRepository.findAllWithRoles();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword("");
        return user;
    }

    @Transactional
    @Override
    public void update(Long id, User user) {
        userRepository.findById(id).ifPresent((u) -> {
            if (!Objects.equals(user.getName(), u.getName())) {
                u.setName(user.getName());
            }
            if (user.getPassword() != null) {
                u.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            if (!Objects.equals(user.isEnabled(), u.isEnabled())) {
                u.setEnabled(user.isEnabled());
            }
        });
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
