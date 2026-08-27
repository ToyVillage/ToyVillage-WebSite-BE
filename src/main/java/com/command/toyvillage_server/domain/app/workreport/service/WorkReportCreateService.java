package com.command.toyvillage_server.domain.app.workreport.service;

import com.command.toyvillage_server.domain.app.task.domain.Task;
import com.command.toyvillage_server.domain.app.task.domain.repository.TaskRepository;
import com.command.toyvillage_server.domain.app.workreport.domain.WorkReport;
import com.command.toyvillage_server.domain.app.workreport.domain.repository.WorkReportRepository;
import com.command.toyvillage_server.domain.app.workreport.exception.WorkReportAlreadyExistsException;
import com.command.toyvillage_server.domain.app.workreport.exception.WorkNotFoundException;
import com.command.toyvillage_server.domain.app.workreport.presentation.dto.request.WorkReportRequest;
import com.command.toyvillage_server.domain.web.file.domain.File;
import com.command.toyvillage_server.domain.web.file.domain.repository.FileRepository;
import com.command.toyvillage_server.domain.web.file.exception.FileNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkReportCreateService {
    private final WorkReportRepository workReportRepository;
    private final TaskRepository taskRepository;
    private final FileRepository fileRepository;

    @Transactional
    public void execute(Long taskId,WorkReportRequest workReportRequest) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> WorkNotFoundException.EXCEPTION);

        if (workReportRepository.existsByTask_Id(taskId)) {
            throw WorkReportAlreadyExistsException.EXCEPTION;
        }

        List<File> files = fileRepository.findAllByFileKeyIn(workReportRequest.fileKey());
        if (files.size() != workReportRequest.fileKey().size())
            throw FileNotFoundException.EXCEPTION;

        WorkReport workReport = WorkReport.builder()
                .task(task)
                .content(workReportRequest.content())
                .note(workReportRequest.note())
                .files(files)
                .build();
        workReportRepository.save(workReport);
    }
}
