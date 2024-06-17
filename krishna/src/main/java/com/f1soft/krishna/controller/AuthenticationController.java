package com.f1soft.krishna.controller;

import com.f1soft.krishna.entity.AppUser;
import com.f1soft.krishna.entity.AuthenticationResponse;
import com.f1soft.krishna.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AppUser request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AppUser request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return  ResponseEntity.ok().body("this can be access without  security");
    }

    @GetMapping("/hi")
    public ResponseEntity<String> hi(){
        return  ResponseEntity.ok().body("this can be access only with user security");
    }
}
