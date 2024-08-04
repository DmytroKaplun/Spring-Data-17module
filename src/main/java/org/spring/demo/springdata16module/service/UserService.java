package org.spring.demo.springdata16module.service;

import lombok.RequiredArgsConstructor;
import org.spring.demo.springdata16module.model.User;
import org.spring.demo.springdata16module.repository.RoleRepository;
import org.spring.demo.springdata16module.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String createUser(User userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            return "User already exists: " + userRequest.getUsername();
        }
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEnabled(Boolean.TRUE);
        roleRepository.findByName("ROLE_USER").ifPresent(user::addRole);
        userRepository.save(user);

        return "User created: " + user.getUsername();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }
}
