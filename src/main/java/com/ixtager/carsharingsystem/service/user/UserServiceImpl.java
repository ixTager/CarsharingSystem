package com.ixtager.carsharingsystem.service.user;

import com.ixtager.carsharingsystem.enumerating.UserRole;
import com.ixtager.carsharingsystem.jpa.UserRepository;
import com.ixtager.carsharingsystem.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = getUserByUsername(username);

        user.setRole(UserRole.DISPATCHER);

        return saveUser(user);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return userRepository.save(user);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getUserByUsername;
    }
}
