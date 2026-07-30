package com.command.toyvillage_server.domain.app.auth.account.exception;

import com.command.toyvillage_server.global.error.exception.ErrorCode;
import com.command.toyvillage_server.global.error.exception.ToyVillageException;

public class AppAccountNotFoundException extends ToyVillageException {
    public static final ToyVillageException EXCEPTION = new AppAccountNotFoundException();

    private AppAccountNotFoundException() {
        super(ErrorCode.APP_ACCOUNT_NOT_FOUND);
    }
}
