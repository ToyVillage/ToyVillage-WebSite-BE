package com.command.toyvillage_server.global.config;

import com.command.toyvillage_server.global.security.jwt.AppJwtProperties;
import com.command.toyvillage_server.global.security.jwt.WebJwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        WebJwtProperties.class,
        AppJwtProperties.class
})
public class JwtConfig {
}
