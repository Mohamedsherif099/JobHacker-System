package com.jobhacker.userservices.security;

import com.jobhacker.userservices.entity.User;
import com.jobhacker.userservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<User> user =userRepository.findByEmail(email);
        if(user.size() > 0){
            if(passwordEncoder.matches(password,user.get(0).getPassword())){
                List<GrantedAuthority>authorities = new ArrayList<>();
//                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                return new UsernamePasswordAuthenticationToken(email,password,authorities);
            }
            else {
                throw new BadCredentialsException("Invalid password");
            }
        }else {
            throw new BadCredentialsException("No user registered with this email");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
