package com.jobhacker.userservices.controller;

import com.jobhacker.userservices.dto.AuthRequestDto;
import com.jobhacker.userservices.dto.JwtAuthenticationResponse;
import com.jobhacker.userservices.entity.User;
import com.jobhacker.userservices.filters.JwtTokenGenerator;
import com.jobhacker.userservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthenticateController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenGenerator jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @GetMapping ("/authenticate")
    public String authenticate(@RequestBody AuthRequestDto authRequestDto){
        String email = authRequestDto.getEmail();
        String password = authRequestDto.getPassword();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        if (authentication.isAuthenticated()){
            UserDetails userDetails =   userDetailsService.loadUserByUsername(email);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse(jwtTokenUtil.generateToken(userDetails));
            return jwtAuthenticationResponse.getToken();
        }
        return null;
    }
    @GetMapping("/welcome")
    public String sayWelcome(){
        return "Welcome to Rest Controller";
    }
}
