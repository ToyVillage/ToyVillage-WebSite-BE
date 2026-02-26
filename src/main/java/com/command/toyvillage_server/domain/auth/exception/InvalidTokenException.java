package com.command.toyvillage_server.domain.auth.exception;

import com.command.toyvillage_server.global.error.exception.ErrorCode;
import com.command.toyvillage_server.global.error.exception.ToyVillageException;

public class InvalidTokenException extends ToyVillageException {
  public static final ToyVillageException EXCEPTION = new InvalidTokenException();

  public InvalidTokenException() {
    super(ErrorCode.INVALID_TOKEN);
  }
}
