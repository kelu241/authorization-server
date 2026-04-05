package com.luciano.authserver;

import org.springframework.security.config.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
public class SecurityFilterConfig {
  @Order(1)
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults());

    http.oauth2ResourceServer(new Customizer<OAuth2ResourceServerConfigurer<HttpSecurity>>() {
      @Override
      public void customize(OAuth2ResourceServerConfigurer<HttpSecurity> conf) {

        conf.jwt(Customizer.withDefaults());
      }
    });

    http.exceptionHandling(new Customizer<ExceptionHandlingConfigurer<HttpSecurity>>() {
      @Override
      public void customize(ExceptionHandlingConfigurer<HttpSecurity> exceptionHandling) {

        exceptionHandling.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));

      }
    });

    return http.build();

  }

}
