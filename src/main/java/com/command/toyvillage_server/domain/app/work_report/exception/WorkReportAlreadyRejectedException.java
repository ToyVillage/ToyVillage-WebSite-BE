package com.command.toyvillage_server.domain.app.work_report.exception;

import com.command.toyvillage_server.global.error.exception.ErrorCode;
import com.command.toyvillage_server.global.error.exception.ToyVillageException;

public class WorkReportAlreadyRejectedException extends ToyVillageException {
    public static final ToyVillageException EXCEPTION = new WorkReportAlreadyRejectedException();

    private WorkReportAlreadyRejectedException() {
        super(ErrorCode.WORK_REPORT_ALREADY_REJECTED);
    }
}
