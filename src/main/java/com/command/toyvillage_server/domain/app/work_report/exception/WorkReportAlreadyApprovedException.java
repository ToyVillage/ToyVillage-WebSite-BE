package com.command.toyvillage_server.domain.app.work_report.exception;

import com.command.toyvillage_server.global.error.exception.ErrorCode;
import com.command.toyvillage_server.global.error.exception.ToyVillageException;

public class WorkReportAlreadyApprovedException extends ToyVillageException {
    public static final ToyVillageException EXCEPTION = new WorkReportAlreadyApprovedException();

    private WorkReportAlreadyApprovedException() {
        super(ErrorCode.WORK_REPORT_ALREADY_APPROVED);
    }
}
