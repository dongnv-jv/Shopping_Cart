package com.example.giohangdemo.service;

import com.example.giohangdemo.domain.User;
import com.example.giohangdemo.repository.IUserRepository;
import com.example.giohangdemo.security.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityServiceCustom implements UserDetailsService {
    @Autowired
    private IUserRepository iUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= iUserRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException(username);
        }

        return new CustomUserDetail(user);
    }
}
