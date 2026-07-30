package com.command.toyvillage_server.global.security.jwt;

import com.command.toyvillage_server.domain.web.auth.admin.domain.WebAdmin;
import com.command.toyvillage_server.domain.web.auth.admin.domain.WebRefreshToken;
import com.command.toyvillage_server.domain.web.auth.admin.domain.repository.WebAdminRepository;
import com.command.toyvillage_server.domain.web.auth.admin.domain.repository.WebRefreshTokenRepository;
import com.command.toyvillage_server.domain.web.auth.admin.exception.WebAdminNotFoundException;
import com.command.toyvillage_server.global.security.auth.WebAdminDetailsService;
import com.command.toyvillage_server.global.security.exception.ExpiredTokenException;
import com.command.toyvillage_server.global.security.exception.InvalidTokenException;
import com.command.toyvillage_server.global.security.exception.RefreshTokenNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class WebJwtTokenProvider {
    private static final String ISSUER = "toy-village-web";
    private static final String CLAIM_TYPE = "type";
    private static final String CLAIM_ROLE = "role";
    private static final String ACCESS_TYPE = "access";
    private static final String REFRESH_TYPE = "refresh";
    private static final String WEB_ADMIN_ROLE = "WEB_ADMIN";

    private final WebJwtProperties properties;
    private final WebAdminRepository webAdminRepository;
    private final WebRefreshTokenRepository webRefreshTokenRepository;
    private final WebAdminDetailsService webAdminDetailsService;

    public TokenPair issue(WebAdmin webAdmin) {
        String email = webAdmin.getEmail();
        String accessToken = createToken(email, WEB_ADMIN_ROLE, ACCESS_TYPE, properties.accessExpiration());
        String refreshToken = createToken(email, WEB_ADMIN_ROLE, REFRESH_TYPE, properties.refreshExpiration());

        webRefreshTokenRepository.save(
                WebRefreshToken.create(email, refreshToken, properties.refreshExpiration())
        );
        return TokenPair.of(accessToken, refreshToken);
    }

    public TokenPair reissue(String token) {
        Claims claims = parseClaims(token);
        validateClaims(claims, REFRESH_TYPE, WEB_ADMIN_ROLE);

        String email = claims.getSubject();
        WebRefreshToken storedToken = webRefreshTokenRepository.findById(email)
                .filter(saved -> saved.getToken().equals(token))
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        WebAdmin webAdmin = webAdminRepository.findByEmail(email)
                .orElseThrow(() -> WebAdminNotFoundException.EXCEPTION);

        webRefreshTokenRepository.delete(storedToken);
        return issue(webAdmin);
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        validateClaims(claims, ACCESS_TYPE, WEB_ADMIN_ROLE);

        UserDetails details = webAdminDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    private String createToken(String subject, String role, String type, long expiration) {
        Date now = new Date();
        return Jwts.builder()
                .issuer(ISSUER)
                .subject(subject)
                .claim(CLAIM_TYPE, type)
                .claim(CLAIM_ROLE, role)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expiration))
                .signWith(signingKey(), Jwts.SIG.HS512)
                .compact();
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(signingKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private void validateClaims(Claims claims, String expectedType, String expectedRole) {
        if (!Objects.equals(ISSUER, claims.getIssuer())
                || !Objects.equals(expectedType, claims.get(CLAIM_TYPE, String.class))
                || !Objects.equals(expectedRole, claims.get(CLAIM_ROLE, String.class))) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private SecretKey signingKey() {
        return Keys.hmacShaKeyFor(properties.secretKey().getBytes(StandardCharsets.UTF_8));
    }
}
