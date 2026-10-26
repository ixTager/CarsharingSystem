package com.ixtager.carsharingsystem.service.auth;

import com.ixtager.carsharingsystem.enumerating.UserRole;
import com.ixtager.carsharingsystem.model.User;
import com.ixtager.carsharingsystem.reponse.JwtAuthenticationResponse;
import com.ixtager.carsharingsystem.request.SignInRequest;
import com.ixtager.carsharingsystem.request.SignUpRequest;
import com.ixtager.carsharingsystem.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {
        var user = User.builder()
                .username(signUpRequest.getUsername())
                .email(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(UserRole.MECHANIC)
                .build();

        userService.createUser(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(),
                signInRequest.getPassword()
        ));

        var user = userService.userDetailsService()
                .loadUserByUsername(signInRequest.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
