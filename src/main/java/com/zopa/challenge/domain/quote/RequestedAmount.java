package com.zopa.challenge.domain.quote;

import com.zopa.challenge.domain.shared.Amount;

public class RequestedAmount extends Amount {

  private static final int MINIMUM_REQUESTED_AMOUNT = 1000;
  private static final int MAXIMUM_REQUESTED_AMOUNT = 15000;

  public RequestedAmount(int amount) {
    super(amount);
    checkThatAmountIsRequestedInOneHundredIncrements(amount);
    checkThatAmountIsBetweenLimits(amount);
  }

  public static RequestedAmount valueOf(String amount) {
    try {
      return new RequestedAmount(Integer.parseInt(amount));
    } catch (NumberFormatException e) {
      throw new InvalidRequestedAmountException("The amount must be a numeric value");
    }
  }

  private void checkThatAmountIsRequestedInOneHundredIncrements(int value) {
    if (value % 100 != 0) {
      throw new InvalidRequestedAmountException("The requested amount must be inform in any £100 increment");
    }
  }

  private void checkThatAmountIsBetweenLimits(int amount) {
    if (amount < MINIMUM_REQUESTED_AMOUNT || amount > MAXIMUM_REQUESTED_AMOUNT) {
      throw new InvalidRequestedAmountException("The requested amount must between £1000 and £15000");
    }
  }

}
