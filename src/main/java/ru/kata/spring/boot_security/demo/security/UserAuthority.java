package ru.kata.spring.boot_security.demo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.boot_security.demo.model.Role;

@RequiredArgsConstructor
public class UserAuthority implements GrantedAuthority {

    private final Role role;

    @Override
    public String getAuthority() {
        return role.getRole();
    }
}
