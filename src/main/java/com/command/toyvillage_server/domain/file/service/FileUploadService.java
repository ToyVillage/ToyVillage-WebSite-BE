package com.command.toyvillage_server.domain.file.service;

import com.command.toyvillage_server.domain.file.domain.File;
import com.command.toyvillage_server.domain.file.domain.repository.FileRepository;
import com.command.toyvillage_server.domain.file.presentation.dto.response.FileUploadResponse;
import com.command.toyvillage_server.global.aws.s3.AwsS3Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Slf4j
public class FileUploadService {
    private final AwsS3Provider awsS3Provider;
    private final FileRepository fileRepository;

    @Transactional
    public FileUploadResponse execute(MultipartFile file) {
        String key = awsS3Provider.upload(file);

        File savedFile = fileRepository.save(
            File.builder()
            .fileKey(key)
            .fileName(StringUtils.getFilename(file.getOriginalFilename()))
            .build()
        );
        log.info("파일{}", savedFile.getId());
        log.info("파일 업로드 완료 / key : {}", key);
        return FileUploadResponse.builder()
            .fileKey(key)
            .build();
    }
}
