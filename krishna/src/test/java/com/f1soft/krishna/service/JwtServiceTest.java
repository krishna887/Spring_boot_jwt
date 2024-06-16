package com.f1soft.krishna.service;

import com.f1soft.krishna.entity.Roles;
import com.f1soft.krishna.entity.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {
    private JwtService jwtService;

    @BeforeEach
    public void setUp() {
        jwtService = new JwtService(); // Or however you instantiate JwtService
    }
User user= User.builder()
        .id(1l)
        .userName("krishna")
        .email("kpchaulagain1999@gmail.com")
        .password("krishna")
        .role(Roles.USER)
        .build();
    @Test
    void extractUsername() {
    }

    @Test
    void isValid() {
    }

    @Test
    void extractClaim() {
    }

    @Test
    void generateToken() {
       String token= jwtService.generateToken(user);
        System.out.println(token);

    }
    @Test
    void getSignInkey(){
       SecretKey secretKey= jwtService.getSigninKey();
        System.out.println(secretKey);


    }
}