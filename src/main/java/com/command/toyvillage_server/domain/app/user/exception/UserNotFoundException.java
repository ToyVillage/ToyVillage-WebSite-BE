package com.command.toyvillage_server.domain.app.user.exception;

import com.command.toyvillage_server.global.error.exception.ErrorCode;
import com.command.toyvillage_server.global.error.exception.ToyVillageException;

public class UserNotFoundException extends ToyVillageException {
  public static final ToyVillageException EXCEPTION = new UserNotFoundException();

  public UserNotFoundException() {
    super(ErrorCode.USER_NOT_FOUND);
  }
}
