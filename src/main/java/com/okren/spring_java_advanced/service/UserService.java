package com.okren.spring_java_advanced.service;

import com.okren.spring_java_advanced.model.User;
import com.okren.spring_java_advanced.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService, IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
//        User byUsername = userRepository.findByUsername(username);
//        return new org.springframework.security.core.userdetails.User(byUsername.getUsername(), byUsername.getPassword(), byUsername.getAuthorities());
    }

    @Override
    public String createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));  // Перш ніж зберігати в базу, пароль необхідно закриптувати
        User savedUser = userRepository.save(user);
        return savedUser.getUsername();
    }
}
