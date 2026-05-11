package com.command.toyvillage_server.global.aws.s3;

import com.command.toyvillage_server.domain.file.domain.repository.FileRepository;
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
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class AwsS3Provider {
    private final S3Client s3Client;
    private final FileRepository fileRepository;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

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
            try {
                delete(key);
            } catch (Exception e1) {
                log.error("파일업로드 실패 후 삭제 작업 실패", e1);
            }
            throw FileUploadFailException.EXCEPTION;
        }

        return key;
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

            fileRepository.findByFileKey(key).ifPresent(fileRepository::delete);
            s3Client.deleteObject(deleteObjectRequest);
        } catch (RuntimeException e) {
            log.error("파일 삭제 실패", e);
            log.error("파일 삭제 실패 / 메시지 : {}", e.getMessage());
            throw FileDeleteFailException.EXCEPTION;
        }
    }

    public void cleanupOrphanedObjects() {
        Instant safeBefore = Instant.now().minus(Duration.ofHours(1));
        List<String> dbKeys = fileRepository.findAllFileKeys();

        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
            .bucket(bucket)
            .build();

        ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsRequest);

        List<ObjectIdentifier> deleteObjects = listObjectsV2Response.contents().stream()
            .filter(o -> o.lastModified() != null && o.lastModified().isBefore(safeBefore))
            .map(S3Object::key)
            .filter(key -> !dbKeys.contains(key))
            .map(key -> ObjectIdentifier.builder().key(key).build())
            .toList();

        if (!deleteObjects.isEmpty()) {
            DeleteObjectsRequest deleteObjectsRequest = DeleteObjectsRequest.builder()
                .bucket(bucket)
                .delete(Delete.builder().objects(deleteObjects).build())
                .build();

            try {
                DeleteObjectsResponse deleteObjectsResponse = s3Client.deleteObjects(deleteObjectsRequest);
                if (!deleteObjectsResponse.errors().isEmpty()) {
                    log.error("고아 객체 삭제 부분 실패: {}", deleteObjectsResponse.errors());
                    throw FileDeleteFailException.EXCEPTION;
                }
                log.info("{} 개의 고아 객체가 삭제되었습니다." , deleteObjects.size());
            } catch (SdkException e) {
                log.error("고아 객체 삭제 실패", e);
                throw FileDeleteFailException.EXCEPTION;
            }
        }
    }
}
