package dev.aloyisius.App_Auth_Server.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain authServerFilterChain(HttpSecurity httpSecurity) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);
        httpSecurity.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());
        httpSecurity.exceptionHandling(
                e -> e.authenticationEntryPoint(
                        new LoginUrlAuthenticationEntryPoint("/login")
                )
        );
        return httpSecurity.build();
    }
    @Bean
    @Order(2)
    public SecurityFilterChain appSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                auth -> {
                  auth.requestMatchers("/").permitAll();
                  auth.anyRequest().authenticated();
        }
        );
        httpSecurity.formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
    @Bean
    public AuthorizationServerSettings authorizationServerSettings(){
        return AuthorizationServerSettings.builder().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository(){
        RegisteredClient r1 = RegisteredClient.withId("spring")
                .clientId("client2")
                .clientSecret(passwordEncoder().encode("secret2"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUri("http://127.0.0.1:8081/login/oauth2/code/gateway-service")

                .scope(OidcScopes.OPENID)
                .build();

        return  new InMemoryRegisteredClientRepository(r1);
    }

}
