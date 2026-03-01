package com.command.toyvillage_server.domain.auth.exception;

import com.command.toyvillage_server.global.error.exception.ErrorCode;
import com.command.toyvillage_server.global.error.exception.ToyVillageException;

public class AdminAlreadyException extends ToyVillageException {
  public static final ToyVillageException EXCEPTION = new AdminAlreadyException();

  public AdminAlreadyException() {
    super(ErrorCode.ADMIN_EXIST);
  }
}
