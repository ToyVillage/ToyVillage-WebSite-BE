package com.command.toyvillage_server.domain.app.work.service;

import com.command.toyvillage_server.domain.app.work.domain.Status;
import com.command.toyvillage_server.domain.app.work.domain.Work;
import com.command.toyvillage_server.domain.app.work.domain.repository.WorkRepository;
import com.command.toyvillage_server.domain.app.work.exception.WorkAlreadyRejectedException;
import com.command.toyvillage_server.domain.app.work.exception.WorkNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkRejectService {
    private final WorkRepository workRepository;

    @Transactional
    public void execute(Long id) {
        Work work = workRepository.findById(id)
                .orElseThrow(() -> WorkNotFoundException.EXCEPTION);

        if (work.getStatus() == Status.REJECTED) {
            throw WorkAlreadyRejectedException.EXCEPTION;
        }

        work.reject();
    }
}
