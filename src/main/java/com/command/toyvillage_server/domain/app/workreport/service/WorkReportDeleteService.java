package com.command.toyvillage_server.domain.app.workreport.service;

import com.command.toyvillage_server.domain.app.workreport.domain.WorkReport;
import com.command.toyvillage_server.domain.app.workreport.domain.repository.WorkReportRepository;
import com.command.toyvillage_server.domain.app.workreport.exception.WorkNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkReportDeleteService {
    private final WorkReportRepository workReportRepository;

    @Transactional
    public void execute(Long id){
        WorkReport workReport = workReportRepository.findById(id)
                .orElseThrow(() -> WorkNotFoundException.EXCEPTION);

        workReportRepository.delete(workReport);
    }
}
