package com.rent.project.productservice.services;


import com.rent.project.productservice.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class CustomUserCredService implements UserDetailsService{

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {


        Claims claims = jwtUtil.extractAllClaims(token);
        String username = jwtUtil.extractUsername(token);
        String password = username;
        Map<Integer ,String> role_name = claims.get("role",Map.class);

        if(username != null){
            return User.builder().username(username).password(password).roles(role_name.get("role_name")).build();
        }else {
            throw new UsernameNotFoundException("User not found..!");
        }

    }

}
