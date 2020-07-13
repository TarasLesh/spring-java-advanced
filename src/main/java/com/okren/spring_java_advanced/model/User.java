package com.okren.spring_java_advanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Collections;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String role;

    @Override
    public String getUsername() {
        return username;   // даний метод буде повертати username
    }

    @Override
    public String getPassword() {
        return password; // даний метод буде повертати password
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  // метод має повертати колекцію GrantedAuthority
        return Collections.singletonList(new SimpleGrantedAuthority(role));  // GrantedAuthority - це інтерфейс, а SimpleGrantedAuthority - це клас, який реалізував даний інтерфейс і який приймає нашу роль.
    }                   // singletonList - List з одним елементом.

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }



}
