package com.oop2.backend.user.controller;

import com.oop2.backend.user.model.AuthRequest;
import com.oop2.backend.user.model.AuthResponse;
import com.oop2.backend.user.model.RegisterRequest;
import com.oop2.backend.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This rest controller handel's the authentication for a @{@link com.oop2.backend.user.model.User}
 * and generates a new jwt token.
 *
 * @author Florian Reining
 * @version 1.0
 */
@RestController
@RequestMapping("/auth")
public class AuthRestController {

    private final AuthService authService;

    @Autowired
    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.OK);
    }

    @PostMapping(value = "/singin")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return new ResponseEntity<>(authService.authenticate(request), HttpStatus.OK);
    }
}
