package com.ethbek.mezion.inventory.service.permission;

import com.ethbek.mezion.inventory.service.config.RestTemplateClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

import static java.util.Objects.isNull;

@Slf4j
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private static final String BEARER = "Bearer ";

    @Value("${dealer.management.validate.user}")
    private String permissionsGetUser;

    @Autowired
    private RestTemplateClient restTemplate;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        String path = request.getRequestURI();
        if (path.contains("/uptime")) {
            filterChain.doFilter(request, response);
            return;
        }


        if (path.contains("/v2/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        if(path.contains("/v3/api-docs")){
            filterChain.doFilter(request, response);
            return;
        }


        if(path.contains("/swagger-ui/")){
            filterChain.doFilter(request, response);
            return;
        }

        if (path.contains("/api/v1/download/")) {
            filterChain.doFilter(request, response);
            return;
        }


        if (isNull(authorizationHeader) || !authorizationHeader.startsWith(BEARER)) {
            throw new TokenAuthenticationException("Token can't be null");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorizationHeader);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        URI uri = new URI(permissionsGetUser);
        ResponseEntity<AuthenticatedUser> authenticatedUserResponse = restTemplate.exchange(
                uri, HttpMethod.GET, entity, AuthenticatedUser.class);
        if (authenticatedUserResponse.getStatusCode() != HttpStatus.OK) {
            throw new TokenAuthenticationException( String.format("Can't authenticate user token=%s", authorizationHeader));
        }

        AuthenticatedUser authenticatedUser =authenticatedUserResponse.getBody();
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        if (authenticatedUser != null){
            authenticatedUser.setAuthenticated(true);
            String authUser = authenticatedUser.toString();
            log.info(authUser);
            log.info("User {} authenticated successfully", authenticatedUser.getEmailAddress());
        }
        filterChain.doFilter(request, response);
    }
}
