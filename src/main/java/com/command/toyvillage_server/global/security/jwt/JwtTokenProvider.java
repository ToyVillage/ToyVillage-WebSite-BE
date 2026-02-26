package com.command.toyvillage_server.global.security.jwt;

import com.command.toyvillage_server.domain.animal.domain.repository.RefreshTokenRepository;
import com.command.toyvillage_server.domain.auth.exception.ExpiredTokenException;
import com.command.toyvillage_server.domain.auth.exception.InvalidTokenException;
import com.command.toyvillage_server.domain.auth.domain.RefreshToken;
import com.command.toyvillage_server.domain.auth.domain.repository.AdminRepository;
import com.command.toyvillage_server.domain.auth.presentation.dto.response.TokenResponse;
import com.command.toyvillage_server.global.security.auth.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final AdminRepository adminRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    private static final String CLAIM_TYPE = "type";
    private static final String ACCESS_TYPE = "access";
    private static final String REFRESH_TYPE = "refresh";

    //access token 생성
    public String createAccessToken(String adminName) {

        Date now = new Date();

        return Jwts.builder()
                .setSubject(adminName)
                .claim(CLAIM_TYPE, ACCESS_TYPE) // 액세스 토큰임을 나타냄
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + jwtProperties.getAccessExpiration())) // 토큰의 만료 시간 설정
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey())
                .compact();

    }

    //refresh token 생성
    public String createRefreshToken(String adminName) {

        Date now = new Date();

        String refreshToken = Jwts.builder()
                .claim(CLAIM_TYPE, REFRESH_TYPE)  //refresh 토큰임을 나타냄
                .setIssuedAt(now)
                .setExpiration(new java.sql.Timestamp(now.getTime() + jwtProperties.getRefreshExpiration()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey())
                .compact();


        refreshTokenRepository.save(
                RefreshToken.builder()
                        .username(adminName)
                        .token(refreshToken)
                        .timeToLive((jwtProperties.getRefreshExpiration()))
                        .build()
        );

        return refreshToken;
    }

    // 토큰에 담겨 있는 userId로 SpringSecurity Authentication 정보를 반환 하는 메서드
    public Authentication getAuthentication(String token) {

        Claims claims = getClaims(token);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Claims getClaims(String token) {

        try {
            return Jwts
                    .parser() //JWT parser 생성
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (ExpiredJwtException e) {
            throw  ExpiredTokenException.EXCEPTION;
        }
        catch (Exception E) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public TokenResponse receiveToken(String adminName) {

        adminRepository.findByUsername(adminName)
                .orElseThrow(() -> AdminNot.EXCEPTION);

        return TokenResponse.builder()
                .accessToken(createAccessToken(organName, client))
                .refreshToken(createRefreshToken(organName, client))
                .organName(organName)
                .build();
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(jwtProperties.getHeader());

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.getPrefix())
                && bearerToken.length() > jwtProperties.getPrefix().length() + 1) {
            return bearerToken.substring(7);
        }

        return null;
    }
}
