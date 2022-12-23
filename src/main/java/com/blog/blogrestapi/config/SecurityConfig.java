package com.blog.blogrestapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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
public class SecurityConfig {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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

    // we can create the users like this or
    // in file application.properties:
    // spring.security.user.name=ana
    // spring.security.user.password=ana
    // spring.security.user.roles=ADMIN
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails ana = User.builder()
                .username("ana")
                .password(passwordEncoder().encode("ana"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(ana, admin);
    }
}
// this class will just create a basic pop-up authentication