package com.zopa.challenge.domain.quote;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class RequestedAmountShould {

  @Test
  public void fail_when_amount_is_not_requested_in_one_hundred_increments() {
    Throwable throwable = catchThrowable(() -> new RequestedAmount(10));

    assertThat(throwable)
        .isInstanceOf(InvalidRequestedAmountException.class)
        .hasMessage("The requested amount must be inform in any £100 increment");
  }

  @Test
  public void fail_when_amount_requested_is_less_than_one_thousand() {
    Throwable throwable = catchThrowable(() -> new RequestedAmount(900));

    assertThat(throwable)
        .isInstanceOf(InvalidRequestedAmountException.class)
        .hasMessage("The requested amount must between £1000 and £15000");
  }

  @Test
  public void fail_when_amount_requested_is_greater_than_fifteen_thousand() {
    Throwable throwable = catchThrowable(() -> new RequestedAmount(15100));

    assertThat(throwable)
        .isInstanceOf(InvalidRequestedAmountException.class)
        .hasMessage("The requested amount must between £1000 and £15000");
  }


  @Test
  public void fail_when_is_created_from_an_invalid_string() {
    Throwable throwable = catchThrowable(() -> RequestedAmount.valueOf("text"));

    assertThat(throwable)
        .isInstanceOf(InvalidRequestedAmountException.class)
        .hasMessage("The amount must be a numeric value");
  }
}