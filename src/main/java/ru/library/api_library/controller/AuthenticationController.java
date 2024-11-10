package ru.library.api_library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import ru.library.api_library.model.Entities.User;
import ru.library.api_library.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    @Operation(summary = "Register", description = "Registration of a new user. Returns 2 tokens", tags = {
            "user", "post" })
    @Parameters({
            @Parameter(name = "request", description = "The entity of the user", required = true)
    })
    public ResponseEntity<?> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Log in to your account. Returns 2 tokens", tags = {
            "user", "post" })
    @Parameters({
            @Parameter(name = "request", description = "The entity of the user", required = true)
    })
    public ResponseEntity<?> login(@RequestBody User request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/refresh_token")
    @Operation(summary = "refreshToken", description = "Updating the current token. Returns 2 tokens", tags = {
            "user", "post" })
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        return authService.refreshToken(request, response);
    }
}