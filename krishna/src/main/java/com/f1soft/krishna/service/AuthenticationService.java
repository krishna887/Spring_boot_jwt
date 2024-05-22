package com.f1soft.krishna.service;

import com.f1soft.krishna.entity.User;
import com.f1soft.krishna.entity.AuthenticationResponse;
import com.f1soft.krishna.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private  final UserInfoRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(User request) {

        User user = new User();
        user.setUserName(request.getUsername());
        user.setRole(request.getRole());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user = repository.save(user);

      String token= jwtService.generateToken(user);
         return new AuthenticationResponse(token);


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
        return new AuthenticationResponse(token);

}
}
