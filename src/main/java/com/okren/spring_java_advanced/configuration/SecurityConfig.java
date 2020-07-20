package com.okren.spring_java_advanced.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("user").roles("USER").and()
                .withUser("admin").password("admin").roles("ADMIN")
                .and()
                .and()
        .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
        ;

//        auth.authenticationProvider(daoAuthenticationProvider());

    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();   -  нешифрування паролю
        return new BCryptPasswordEncoder();    // -  шифрування паролю
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/director").hasRole("ADMIN")   // POST з /director може викликати лише ADMIN
                .antMatchers("/movies/*").hasRole("ADMIN")    // /movies/* (тобто після movies/ ще якісь параметри) може викликати лише ADMIN
                .antMatchers("/user").anonymous()  // юзера зможуть додавати аноніми
                .anyRequest().authenticated()   // решту реквестів - будь-який аутентифікований користувач
                .and()
                .httpBasic();
    }
}
