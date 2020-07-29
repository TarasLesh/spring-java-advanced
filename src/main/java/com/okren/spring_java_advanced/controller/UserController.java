package com.okren.spring_java_advanced.controller;

import com.okren.spring_java_advanced.dtos.AuthenticationRequest;
import com.okren.spring_java_advanced.dtos.AuthenticationResponse;
import com.okren.spring_java_advanced.model.User;
import com.okren.spring_java_advanced.service.IUserService;
import com.okren.spring_java_advanced.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public String registerUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate (@RequestBody AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));
        String token = jwtService.generateToken(authenticationRequest.getUsername());
        return new AuthenticationResponse(token);
    }
}

