package com.command.toyvillage_server.global.config;

import com.command.toyvillage_server.global.security.jwt.AppJwtTokenProvider;
import com.command.toyvillage_server.global.security.jwt.WebJwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final ObjectMapper objectMapper;
    private final WebJwtTokenProvider webJwtTokenProvider;
    private final AppJwtTokenProvider appJwtTokenProvider;

    @Value("${cors.allowed-origins.main}")
    private String prodUrl;

    @Value("${cors.allowed-origins.dev}")
    private String stagUrl;

    @Value("${cors.allowed-origins.local}")
    private String localUrl;

//    @Value("${cors.allowed-origins.vercel-url}") // 버셀로 지환이가 배포하면 추가할 예정
//    private String vercelUrl;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                    // web administrator auth
                    .requestMatchers(
                            "/web/auth/login",
                            "/web/auth/signup",
                            "/web/auth/reissue",
                            "/web/auth/password",
                            "/web/auth/password/verification",
                            "/web/auth/password/verification/confirm"
                    ).permitAll()

                    // app account auth and administration
                    .requestMatchers("/app/auth/login", "/app/auth/reissue").permitAll()
                    .requestMatchers(HttpMethod.PATCH, "/app/auth/password")
                            .hasAnyRole("APP_ADMIN", "EMPLOYEE")
                    .requestMatchers("/app/admin", "/app/admin/**").hasRole("APP_ADMIN")

                    // public website reads
                    .requestMatchers(HttpMethod.GET,
                            "/faq", "/faq/**",
                            "/gallery", "/gallery/**",
                            "/news", "/news/**",
                            "/events", "/events/**",
                            "/animal", "/animal/**",
                            "/popup", "/popup/**"
                    ).permitAll()

                    // public partnership submission
                    .requestMatchers(HttpMethod.POST, "/partnership").permitAll()

                    // web administrator writes
                    .requestMatchers(
                            "/faq", "/faq/**",
                            "/gallery", "/gallery/**",
                            "/news", "/news/**",
                            "/events", "/events/**",
                            "/animal", "/animal/**",
                            "/popup", "/popup/**",
                            "/partnership", "/partnership/**"
                    ).hasRole("WEB_ADMIN")

                    // file upload is shared by the two administrator systems
                    .requestMatchers(HttpMethod.POST, "/file")
                            .hasAnyRole("WEB_ADMIN", "APP_ADMIN")

                    // app workflow administration
                    .requestMatchers("/team", "/team/**", "/join-team", "/join-team/**")
                            .hasRole("APP_ADMIN")
                    .requestMatchers("/reservation/permission", "/reservation/permission/**")
                            .hasRole("APP_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/reservation", "/reservation/**")
                            .hasAnyRole("APP_ADMIN", "EMPLOYEE")
                    .requestMatchers("/reservation", "/reservation/**").hasRole("APP_ADMIN")

                    // app workflow reads and writes
                    .requestMatchers(HttpMethod.GET,
                            "/close-day", "/close-day/**",
                            "/open-time", "/open-time/**",
                            "/notice", "/notice/**",
                            "/documents", "/documents/**"
                    ).hasAnyRole("APP_ADMIN", "EMPLOYEE")
                    .requestMatchers(
                            "/close-day", "/close-day/**",
                            "/open-time", "/open-time/**",
                            "/notice", "/notice/**",
                            "/documents", "/documents/**"
                    ).hasRole("APP_ADMIN")

                    .anyRequest().denyAll()
                )
                .with(
                        new SecurityFilterConfig(
                                webJwtTokenProvider,
                                appJwtTokenProvider,
                                objectMapper
                        ),
                        Customizer.withDefaults()
                )
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(
                prodUrl, stagUrl, localUrl, "https://toyvillage.vercel.app/", "https://toyvillage.kr/", "http://localhost:5173/"
        ));

        configuration.setAllowedMethods(Arrays.asList("OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE"));
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
