package com.flashmeet.gateway.gateway.WebFilters;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.boot.autoconfigure.mustache.MustacheProperties.Reactive;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.lang.NonNull;
import com.flashmeet.gateway.gateway.componentes.JWT.DecodificarToken;
import com.flashmeet.gateway.gateway.componentes.JWT.ValidadorJWT;
import reactor.core.publisher.Mono;

public class JWTFilter implements WebFilter {

    private final ValidadorJWT validadorJWT;

    private final DecodificarToken decodificarToken;

    public JWTFilter(ValidadorJWT validadorJWT, DecodificarToken decodificarToken) {
        this.validadorJWT = validadorJWT;
        this.decodificarToken = decodificarToken;
    }

    /*
     * @Override
     * protected void doFilterInternal(HttpServletRequest request,
     * HttpServletResponse response, FilterChain filterChain)
     * throws ServletException, IOException {
     * 
     * if (request.getServletPath().equals("/auth/iniciarSesion")
     * || request.getServletPath().equals("/auth/register")) {
     * filterChain.doFilter(request, response);
     * 
     * return;
     * }
     * 
     * String tokenString = getToken(request.getHeader("Authorization"));
     * 
     * if(validadorJWT.validarToken(tokenString)){
     * Authentication authentication = getAuthentication(tokenString);
     * SecurityContextHolder.getContext().setAuthentication(authentication);
     * filterChain.doFilter(request, response);
     * return;
     * }else{
     * response.setStatus(401);
     * }
     * 
     * }
     */

    private String getToken(String header) {

        if (header != null && header.startsWith("Bearer")) {
            return header.substring(7);
        } else {
            return null;
        }
    }

    public Authentication getAuthentication(String token) {
        String idUsuario = decodificarToken.getUserIdFromToken(token);
        List<GrantedAuthority> authorities = decodificarToken.getAuthoritiesFromToken(token);
        return new UsernamePasswordAuthenticationToken(idUsuario, null, authorities);
    }

    @Override
    public @NonNull Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        if ("/auth/auth/iniciarSesion".equals(path) || "/auth/auth/signup".equals(path)) {
            return chain.filter(exchange);
        }

        return Mono.justOrEmpty(getToken(exchange.getRequest().getHeaders().getFirst("Authorization")))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED))) // Si no hay token,
                                                                                                 // error 401
                .flatMap(token -> {
                    if (!validadorJWT.validarToken(token)) {
                        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED));
                    }
                    return Mono.just(getAuthentication(token));
                })
                .flatMap(authentication -> {
                    URI uri = UriComponentsBuilder.fromUri(exchange.getRequest().getURI())
                            .replaceQueryParam("userId", authentication.getName()).build().toUri();

                    ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                            .uri(uri)
                            .build();

                    ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();

                    SecurityContextImpl context = new SecurityContextImpl(authentication);
                    return chain.filter(mutatedExchange)
                            .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));
                });
    }
}
