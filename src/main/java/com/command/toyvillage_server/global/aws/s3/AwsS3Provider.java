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
            delete(key);
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
        } catch (SdkException e) {
            log.error("파일 삭제 실패", e);
            log.error("파일 삭제 실패 / 메시지 : {}", e.getMessage());
            throw FileDeleteFailException.EXCEPTION;
        }
    }

    public void cleanupOrphanedObjects() {
        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
            .bucket(bucket)
            .build();

        ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsRequest);

        List<String> keys = listObjectsV2Response.contents().stream()
            .map(S3Object::key)
            .toList();

        List<String> dbKeys = fileRepository.findAllFileKeys();

        List<ObjectIdentifier> deleteObjects = keys.stream()
            .filter(key -> !dbKeys.contains(key))
            .map(key -> ObjectIdentifier.builder().key(key).build())
            .toList();

        if (!deleteObjects.isEmpty()) {
            DeleteObjectsRequest deleteObjectsRequest = DeleteObjectsRequest.builder()
                .bucket(bucket)
                .delete(Delete.builder().objects(deleteObjects).build())
                .build();

            s3Client.deleteObjects(deleteObjectsRequest);
            System.out.println(deleteObjects.size() + " 개의 고아 객체가 삭제되었습니다.");
        }
    }
}
