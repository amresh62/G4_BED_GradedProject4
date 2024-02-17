package com.gl.employeeManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gl.employeeManagement.service.impl.userServicesImpl;

@Configuration
public class WebSecurity {

    @Bean
    public UserDetailsService userDetailsService() {
        return new userServicesImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.GET, "/employees", "/users", "roles")
                        .hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/employees").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/employees/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/employees").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/users").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/roles").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/roles").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/roles").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}