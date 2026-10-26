package com.ixtager.carsharingsystem.service.user;

import com.ixtager.carsharingsystem.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    User getUserByUsername(String username);
    User getCurrentUser();

    User saveUser(User user);
    User createUser(User user);

    UserDetailsService userDetailsService();
}
