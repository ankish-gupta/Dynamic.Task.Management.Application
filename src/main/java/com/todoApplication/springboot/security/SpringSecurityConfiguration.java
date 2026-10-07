package com.todoApplication.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {

//    we will not use this as "withDefaultPasswordEncoder()" is deprecated
//    @Bean
//    public InMemoryUserDetailsManager userPasswordEncoder(){
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("ankish").password("1111").roles("USER","ADMIN").build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager(){
        System.out.println("Creating user details manager");
        UserDetails userDetails1 = getUserDetails("ankish", "1111");
        UserDetails userDetails2 = getUserDetails("ankishgupta", "1234");

        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }


    private UserDetails getUserDetails(String username, String password) {
        Function<String,String> passwordEncoder =
                input  -> passwordEncoder().encode(input);

        return User
                .builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER","ADMIN")
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
