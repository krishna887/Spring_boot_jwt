package com.f1soft.krishna.service;

import com.f1soft.krishna.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserInfoService implements UserDetailsService {

private final UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userInfoRepository.findByUserName(username)
               .orElseThrow(()-> new UsernameNotFoundException("user not find of this type"));
    }
}
