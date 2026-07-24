package com.command.toyvillage_server.domain.app.notice.service;

import com.command.toyvillage_server.domain.app.notice.domain.Notice;
import com.command.toyvillage_server.domain.app.notice.domain.repository.NoticeRepository;
import com.command.toyvillage_server.domain.app.notice.exception.NoticeNotFoundException;
import com.command.toyvillage_server.domain.app.notice.presentation.dto.request.NoticeRequestDto;
import com.command.toyvillage_server.domain.web.file.domain.File;
import com.command.toyvillage_server.domain.web.file.domain.repository.FileRepository;
import com.command.toyvillage_server.domain.web.file.exception.FileNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeUpdateService {
    private final NoticeRepository noticeRepository;
    private final FileRepository fileRepository;


    @Transactional
    public void execute(Long id, NoticeRequestDto dto) {
        List<String> fileKeys = dto.getFiles() == null ? new ArrayList<>() : dto.getFiles();
        List<File> files = fileRepository.findAllByFileKeyIn(fileKeys);
        if (files.size() != fileKeys.size())
            throw FileNotFoundException.EXCEPTION;

        Notice notice = noticeRepository.findById(id)
            .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        notice.update(dto.getTitle(), dto.getKind(), dto.getContent(),  files);
        noticeRepository.save(notice);
    }
}
