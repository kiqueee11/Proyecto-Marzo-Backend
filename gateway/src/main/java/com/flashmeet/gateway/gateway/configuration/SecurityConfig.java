package com.flashmeet.gateway.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.SessionManagementSpec;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.SessionLimit;

import com.flashmeet.gateway.gateway.WebFilters.JWTFilter;
import com.flashmeet.gateway.gateway.componentes.JWT.DecodificarToken;
import com.flashmeet.gateway.gateway.componentes.JWT.ValidadorJWT;

@Configuration
@EnableMethodSecurity
@EnableWebFluxSecurity

public class SecurityConfig {

    @Bean
    public DecodificarToken decodificarToken() {
        return new DecodificarToken();
    }

    @Bean
    public ValidadorJWT validadorJWT() {
        return new ValidadorJWT();
    }

    @Bean

    public JWTFilter jwtFilter() {
        return new JWTFilter(validadorJWT(), decodificarToken());
    }

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity serverHttpSecurity) throws Exception {

        serverHttpSecurity.csrf(csrf -> csrf.disable()).logout(logout -> logout.disable())
                .authorizeExchange(
                        req -> req.pathMatchers(HttpMethod.POST, "/auth/auth/iniciarSesion", "/auth/auth/signup")
                                .permitAll().anyExchange().authenticated())
                .addFilterAt(jwtFilter(), SecurityWebFiltersOrder.AUTHENTICATION);
        return serverHttpSecurity.build();

    }

}
