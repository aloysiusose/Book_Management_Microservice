package dev.aloyisius.Bookcatalogservice.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String jwksUrl = "http://localhost:8080/oauth2/jwks" ;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                auth -> {
                    auth.requestMatchers("/api/v1/books/**").hasAnyAuthority("ADMIN");
                    auth.anyRequest().authenticated();
                }
        );
        httpSecurity.oauth2ResourceServer(oauth -> oauth.jwt(
                jwt -> jwt.jwkSetUri(jwksUrl)
        ));
        return httpSecurity.build();
    }
}
