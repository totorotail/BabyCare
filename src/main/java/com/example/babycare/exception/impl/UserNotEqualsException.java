package com.example.babycare.exception.impl;

import com.example.babycare.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class UserNotEqualsException extends AbstractException {

  @Override
  public int getStatusCode() {
    return HttpStatus.BAD_REQUEST.value();
  }

  @Override
  public String getMessage() {
    return "유저가 다릅니다.";
  }
}
