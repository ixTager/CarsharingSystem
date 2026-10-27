package com.ixtager.carsharingsystem.controller;

import com.ixtager.carsharingsystem.reponse.JwtAuthenticationResponse;
import com.ixtager.carsharingsystem.request.SignInRequest;
import com.ixtager.carsharingsystem.request.SignUpRequest;
import com.ixtager.carsharingsystem.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication controller")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    @Operation(
            summary = "Sign up",
            description = "Sign up; return JwtAuthenticationResponse"
    )
    public JwtAuthenticationResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/sign-in")
    @Operation(
            summary = "Sign In",
            description = "Sign in; return JwtAuthenticationResponse"
    )
    public JwtAuthenticationResponse signIn(@Valid @RequestBody SignInRequest signInRequest) {
        return authenticationService.signIn(signInRequest);
    }
}
