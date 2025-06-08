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

    private static final String LAMBDA_ROLE = "LAMBDA";
    private static final String SWAGGER_ROLE = "SWAGGER";
    private String lambdaUsername;
    private String lambdaPassword;
    private String swaggerUsername;
    private String swaggerPassword;

    @Bean
    @Order(1)
    SecurityFilterChain basicSecurityFilterChain(HttpSecurity http) throws Exception {
        return http.securityMatchers(matchers -> matchers.requestMatchers(HttpMethod.POST, "/users")
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/openapi.yml"))
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
        UserDetails lambdaUser = User.withUsername(lambdaUsername).password(lambdaPassword).roles(LAMBDA_ROLE).build();
        UserDetails swaggerUser = User.withUsername(swaggerUsername).password(swaggerPassword).roles(SWAGGER_ROLE).build(); 
        return new InMemoryUserDetailsManager(lambdaUser, swaggerUser);
    }

}
