package com.command.toyvillage_server.domain.app.work_report.exception;

import com.command.toyvillage_server.global.error.exception.ErrorCode;
import com.command.toyvillage_server.global.error.exception.ToyVillageException;

public class WorkReportNotFoundExeception extends ToyVillageException {
    public static final ToyVillageException EXCEPTION = new WorkReportNotFoundExeception();

    private WorkReportNotFoundExeception() {
        super(ErrorCode.WORK_REPORT_NOT_FOUNT);
    }
}
