package org.spring.demo.springdata16module.controller;

import lombok.RequiredArgsConstructor;
import org.spring.demo.springdata16module.model.User;
import org.spring.demo.springdata16module.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping()
    public String hello() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        return "hello " + auth.getName();
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        return userService.createUser(user);
    }
}
