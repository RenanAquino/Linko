package com.study.linko.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.linko.dto.auth.LoginDTO;
import com.study.linko.dto.auth.RegisterDTO;
import com.study.linko.service.auth.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDTO data) {
        authService.register(data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(LoginDTO data) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(data));
    }
}
