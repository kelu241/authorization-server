package com.luciano.authserver;

import org.springframework.security.config.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
public class SecurityFilterConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.oauth2AuthorizationServer(oauth2 -> oauth2.oidc(Customizer.withDefaults()));

    http.exceptionHandling(
        exceptons -> exceptons.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")));

    http.oauth2ResourceServer(conf -> conf.jwt(Customizer.withDefaults()));

    return http.build();

  }

}
