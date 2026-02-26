package com.command.toyvillage_server.domain.animal.exception;

import com.command.toyvillage_server.global.error.exception.ErrorCode;
import com.command.toyvillage_server.global.error.exception.ToyVillageException;

public class ExpiredTokenException extends ToyVillageException {
  public static final ToyVillageException EXCEPTION = new ExpiredTokenException();

  public ExpiredTokenException() {
    super(ErrorCode.EXPIRED_TOKEN);
  }
}
