package com.command.toyvillage_server.domain.app.work_report.exception;

import com.command.toyvillage_server.global.error.exception.ErrorCode;
import com.command.toyvillage_server.global.error.exception.ToyVillageException;

public class WorkNotFoundException extends ToyVillageException {
    public static final ToyVillageException EXCEPTION = new WorkNotFoundException();

    private WorkNotFoundException() {
        super(ErrorCode.WORK_NOT_FOUND);
    }
}
