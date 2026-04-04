package com.command.toyvillage_server.domain.file.service;

import com.command.toyvillage_server.domain.file.presentation.dto.response.FileUploadResponse;
import com.command.toyvillage_server.global.aws.s3.AwsS3Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Slf4j
public class FileUploadService {
    private final AwsS3Provider awsS3Provider;

    @Transactional
    public FileUploadResponse execute(MultipartFile file) {
        String key = awsS3Provider.upload(file);
        String fileUrl = awsS3Provider.getPresignedUrl(key);

        log.info("파일 업로드 완료 / key : {}", key);
        return FileUploadResponse.builder()
            .key(key)
            .fileUrl(fileUrl)
            .build();
    }
}
