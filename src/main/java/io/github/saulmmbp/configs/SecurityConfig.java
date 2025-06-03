package io.github.saulmmbp.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.Setter;

@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.security.basic")
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final String ROLE = "LAMBDA";
    private String username;
    private String password;

    @Bean
    @Order(1)
    SecurityFilterChain basicSecurityFilterChain(HttpSecurity http) throws Exception {
        return http.securityMatchers(matchers -> matchers.requestMatchers(HttpMethod.POST, "/users"))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> req.anyRequest().authenticated()).httpBasic(Customizer.withDefaults())
                .cors(Customizer.withDefaults()).csrf(CsrfConfigurer::disable).build();
    }

    @Bean
    @Order(2)
    SecurityFilterChain cognitoSecurityFilterChain(HttpSecurity http) throws Exception {
        return http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> req.anyRequest().authenticated())
                .oauth2ResourceServer(customizer -> customizer.jwt(Customizer.withDefaults()))
                .cors(Customizer.withDefaults()).csrf(CsrfConfigurer::disable).build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername(username).password(password).roles(ROLE).build();
        return new InMemoryUserDetailsManager(user);
    }

}
