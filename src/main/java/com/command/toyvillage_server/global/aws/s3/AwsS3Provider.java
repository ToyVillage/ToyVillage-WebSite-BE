package com.command.toyvillage_server.global.aws.s3;

import com.command.toyvillage_server.global.aws.s3.exception.FileDeleteFailException;
import com.command.toyvillage_server.global.aws.s3.exception.FileEmptyException;
import com.command.toyvillage_server.global.aws.s3.exception.FileUploadFailException;
import com.command.toyvillage_server.global.aws.s3.exception.KeyEmptyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class AwsS3Provider {
    private final S3Client s3Client;
    private final S3Presigner s3Presigner;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${spring.cloud.aws.s3.url-duration}")
    private Long urlDuration;

    public String upload(MultipartFile file) {
        if(file == null || file.isEmpty()) {
            throw FileEmptyException.EXCEPTION;
        }

        String key = UUID.randomUUID() + "_" + file.getOriginalFilename();
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(bucket)
            .contentType(file.getContentType())
            .key(key)
            .build();
        try {
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        } catch (IOException | SdkException e) {
            log.error("파일 업로드 실패", e);
            log.error("파일 업로드 실패 / 메시지 : {}", e.getMessage());
            throw FileUploadFailException.EXCEPTION;
        }

        return key;
    }

    public String getPresignedUrl(String key) {
        if (key == null || key.isBlank()) {
            throw KeyEmptyException.EXCEPTION;
        }

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(urlDuration))
            .getObjectRequest(getObjectRequest)
            .build();

        return s3Presigner.presignGetObject(presignRequest).url().toString();
    }

    public void delete(String key) {
        if(key == null || key.isBlank()) {
            throw KeyEmptyException.EXCEPTION;
        }

        try {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

            s3Client.deleteObject(deleteObjectRequest);
        } catch (SdkException e) {
            log.error("파일 삭제 실패", e);
            log.error("파일 삭제 실패 / 메시지 : {}", e.getMessage());
            throw FileDeleteFailException.EXCEPTION;
        }
    }

}
