package com.blog.blogrestapi.config;

import com.blog.blogrestapi.security.JwtAuthenticationEntryPoint;
import com.blog.blogrestapi.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
// with this annotation, this class becomes a java based configuration
// and within this we can define all the spring bean definitions
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    // instead of injecting the class object directly,
    // we use the interface to achieve the loose coupling
    private final UserDetailsService userDetailsService;

    // this class will execute whenever an unauthorized user tries to access the protected resource
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    private final JwtAuthenticationFilter authenticationFilter;

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
                                .requestMatchers("/api/auth/**").permitAll() // all the users can access the login url
                                .anyRequest().authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
// this class will just create a basic pop-up authentication