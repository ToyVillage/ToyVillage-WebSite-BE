package com.command.toyvillage_server.domain.app.user.exception;

import com.command.toyvillage_server.global.error.exception.ErrorCode;
import com.command.toyvillage_server.global.error.exception.ToyVillageException;

public class UserAlreadyException extends ToyVillageException {
    public static final ToyVillageException EXCEPTION = new UserAlreadyException();

    public UserAlreadyException() {
        super(ErrorCode.USER_EXIST);
    }
}
