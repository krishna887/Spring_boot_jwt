package com.f1soft.krishna.service;

import com.f1soft.krishna.entity.Roles;
import com.f1soft.krishna.entity.User;
import com.f1soft.krishna.entity.AuthenticationResponse;
import com.f1soft.krishna.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private  final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public String register(User request) {
       User user= User.builder()
        .userName(request.getUsername())
        .email(request.getEmail())
        .role(request.getRole())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .build();
       repository.save(user);
       return "User register Successfully";

    }
    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user =repository.findByUserName(request.getUsername()).orElseThrow();
        String token= jwtService.generateToken(user);

        return new AuthenticationResponse(token, user.getUsername(), (List<? extends GrantedAuthority>) user.getAuthorities());

}
}
