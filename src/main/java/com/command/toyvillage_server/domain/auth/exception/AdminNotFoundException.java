package com.command.toyvillage_server.domain.auth.exception;

import com.command.toyvillage_server.global.error.exception.ErrorCode;
import com.command.toyvillage_server.global.error.exception.ToyVillageException;

public class AdminNotFoundException extends ToyVillageException {
    public static final ToyVillageException EXCEPTION = new AdminNotFoundException();

    public AdminNotFoundException() {
        super(ErrorCode.ADMIN_NOT_FOUND);
    }
}
