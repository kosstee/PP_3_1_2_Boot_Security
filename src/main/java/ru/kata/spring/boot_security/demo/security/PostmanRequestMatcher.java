package ru.kata.spring.boot_security.demo.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Objects;

public class PostmanRequestMatcher implements RequestMatcher {

    @Override
    public boolean matches(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return Objects.nonNull(userAgent) && userAgent.contains("Postman");
    }
}