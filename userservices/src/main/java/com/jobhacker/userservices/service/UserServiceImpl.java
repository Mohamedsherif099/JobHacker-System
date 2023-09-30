package com.jobhacker.userservices.service;


import com.jobhacker.userservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password = null;
        List<GrantedAuthority>authorities = null;
        List<com.jobhacker.userservices.entity.User> users = userRepository.findByEmail(username);

        if (users.size() == 0) {
            throw new UsernameNotFoundException("User details not found for the user " + username);
        }
        else {
            userName = users.get(0).getEmail();
            password = users.get(0).getPassword();
            authorities =new ArrayList<>();

        }
        return new User(userName, password, authorities);
    }




}




