package com.example.babycare.exception.impl;

import com.example.babycare.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class NoUserException extends AbstractException {

  @Override
  public int getStatusCode() {
    return HttpStatus.BAD_REQUEST.value();
  }

  @Override
  public String getMessage() {
    return "해당 유저가 존재하지 않습니다.";
  }
}

