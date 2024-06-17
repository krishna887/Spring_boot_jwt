package com.f1soft.krishna.service;

import com.f1soft.krishna.entity.AppUser;
import com.f1soft.krishna.entity.AuthenticationResponse;
import com.f1soft.krishna.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private  final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public String register(AppUser request) {
       AppUser appUser = AppUser.builder()
        .userName(request.getUsername())
        .email(request.getEmail())
        .role(request.getRole())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .build();
       repository.save(appUser);
       return jwtService.generateToken(appUser);

    }
    public AuthenticationResponse authenticate(AppUser request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        AppUser appUser =repository.findByUserName(request.getUsername()).orElseThrow();
        String token= jwtService.generateToken(appUser);

        return new AuthenticationResponse(token, appUser.getUsername(), (List<? extends GrantedAuthority>) appUser.getAuthorities());

}
}
