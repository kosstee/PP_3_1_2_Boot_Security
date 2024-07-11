package ru.kata.spring.boot_security.demo.initialization;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UsersService;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DbInit {

    private final UsersService usersService;
    private final RoleRepository roleRepository;

    @PostConstruct
    private void postConstruct() {
        User admin = new User(
                "admin",
                "admin",
                "Mike",
                true,
                roleRepository.save(new Role("ROLE_ADMIN")));
        usersService.save(admin);

        User user = new User(
                "user",
                "user",
                "Ivan",
                true,
                roleRepository.save(new Role("ROLE_USER")));
        usersService.save(user);
    }
}
