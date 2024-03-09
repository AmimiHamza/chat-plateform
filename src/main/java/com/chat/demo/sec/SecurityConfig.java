package com.chat.demo.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import com.chat.demo.users.User;
import com.chat.demo.users.UserRepo;

import static org.springframework.security.config.Customizer.withDefaults;


import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserRepo userRepo;


    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            User user = userRepo.findByEmail(email);
            if (user == null) throw new UsernameNotFoundException("User not found with username or email : " + email);

            return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                    .password(passwordEncoder().encode(user.getPassword()))
                    .roles(user.getRole())
                    .build();
        };
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/h2-console/**").hasRole("ADMIN")
                    .requestMatchers("/public/**").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(withDefaults());
            http.headers().frameOptions().sameOrigin();

            http.csrf().disable();

        return http.build();
    }
}
