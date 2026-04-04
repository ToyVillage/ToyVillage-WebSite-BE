package com.command.toyvillage_server.global.aws.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class AwsS3Config {
    @Bean
    public AwsCredentialsProvider awsCredentialsProvider(
        @Value("${spring.cloud.aws.credentials.access-key}") String accessKey,
        @Value("${spring.cloud.aws.credentials.secret-key}") String secretKey
    ) {
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey));
    }

    @Bean
    public S3Client s3Client(
        @Value("${spring.cloud.aws.region.static}") String region,
        @Value("${spring.cloud.aws.s3.region:}") String s3Region,
        AwsCredentialsProvider credentialsProvider
    ) {
        String finalRegion = StringUtils.hasText(s3Region) ? s3Region : region;

        return S3Client.builder()
            .credentialsProvider(credentialsProvider)
            .region(Region.of(finalRegion))
            .build();
    }

    @Bean
    public S3Presigner s3Presigner(
        @Value("${spring.cloud.aws.region.static}") String region,
        @Value("${spring.cloud.aws.s3.region:}") String s3Region,
        AwsCredentialsProvider credentialsProvider
    ) {
        String finalRegion = StringUtils.hasText(s3Region) ? s3Region : region;

        return S3Presigner.builder()
            .credentialsProvider(credentialsProvider)
            .region(Region.of(finalRegion))
            .build();
    }
}
