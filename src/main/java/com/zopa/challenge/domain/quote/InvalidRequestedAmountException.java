package com.zopa.challenge.domain.quote;

public class InvalidRequestedAmountException extends RuntimeException {
  public InvalidRequestedAmountException(String message) {
    super(message);
  }
}
