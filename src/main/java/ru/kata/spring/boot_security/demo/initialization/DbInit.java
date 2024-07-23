package ru.kata.spring.boot_security.demo.initialization;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UsersService;

@Component
@RequiredArgsConstructor
public class DbInit {

    private final UsersService usersService;
    private final RoleRepository roleRepository;

    @PostConstruct
    private void postConstruct() {
        Role roleAdmin = roleRepository.save(new Role("ROLE_ADMIN"));
        User admin = new User(
                "admin",
                "Donald",
                "Duck",
                50,
                "25cent@duckduck.go",
                "admin",
                true,
                roleAdmin);
        usersService.save(admin);

        Role roleUser = roleRepository.save(new Role("ROLE_USER"));
        User user = new User(
                "user",
                "Mickey",
                "Mouse",
                18,
                "cheeser@mouse.go",
                "user",
                true,
                roleUser);
        usersService.save(user);
    }
}
