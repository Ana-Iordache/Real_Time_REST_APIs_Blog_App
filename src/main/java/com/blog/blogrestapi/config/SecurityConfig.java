package com.blog.blogrestapi.config;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// with this annotation, this class becomes a java based configuration
// and within this we can define all the spring bean definitions
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    // instead of injecting the class object directly,
    // we use the interface to achieve the loose coupling
    private final UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // this AuthenticationManager will use userDetailsService to get the user from de DB and also
    // uses PasswordEncoder to encode the password
    // spring security provides automatically UserDetailsService and PasswordEncoder to AuthenticationManager
    // so we don't need to explicitly specify it
    // SO --> this method will do the DB authentication
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((auth) ->
                        auth.requestMatchers(HttpMethod.GET, "/api/**").permitAll() // we give permission to access the GET endpoint to all the users
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
// this class will just create a basic pop-up authentication