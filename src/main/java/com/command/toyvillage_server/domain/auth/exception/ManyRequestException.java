package com.command.toyvillage_server.domain.auth.exception;

import com.command.toyvillage_server.global.error.exception.ErrorCode;
import com.command.toyvillage_server.global.error.exception.ToyVillageException;

public class ManyRequestException extends ToyVillageException {
  public static final ToyVillageException EXCEPTION = new ManyRequestException();

  public ManyRequestException() {
    super(ErrorCode.MANY_REQUEST);
  }
}
