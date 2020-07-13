package com.okren.spring_java_advanced.controller;

import com.okren.spring_java_advanced.model.User;
import com.okren.spring_java_advanced.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public String registerUser(@RequestBody User user){
        return userService.createUser(user);
    }

}

