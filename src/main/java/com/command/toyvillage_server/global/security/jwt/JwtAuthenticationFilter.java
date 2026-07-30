package com.command.toyvillage_server.global.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final WebJwtTokenProvider webJwtTokenProvider;
    private final AppJwtTokenProvider appJwtTokenProvider;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String token = resolveToken(request);

        if (token != null) {
            Authentication authentication = authenticate(token);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }

    private Authentication authenticate(String token) {
        try {
            return webJwtTokenProvider.getAuthentication(token);
        } catch (RuntimeException ignored) {
            try {
                return appJwtTokenProvider.getAuthentication(token);
            } catch (RuntimeException ignoredAppToken) {
                return null;
            }
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken)
                && bearerToken.startsWith(BEARER_PREFIX)
                && bearerToken.length() > BEARER_PREFIX.length()) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        return null;
    }
}
