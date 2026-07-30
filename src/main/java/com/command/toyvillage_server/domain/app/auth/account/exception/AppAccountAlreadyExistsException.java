package com.command.toyvillage_server.domain.app.auth.account.exception;

import com.command.toyvillage_server.global.error.exception.ErrorCode;
import com.command.toyvillage_server.global.error.exception.ToyVillageException;

public class AppAccountAlreadyExistsException extends ToyVillageException {
    public static final ToyVillageException EXCEPTION = new AppAccountAlreadyExistsException();

    private AppAccountAlreadyExistsException() {
        super(ErrorCode.APP_ACCOUNT_EXIST);
    }
}
