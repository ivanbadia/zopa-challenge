package com.zopa.challenge.domain.lender;

import com.zopa.challenge.domain.shared.Amount;
import com.zopa.challenge.domain.shared.Rate;

public class Lender {
  private final String name;
  private final Rate rate;
  private final Amount availableAmount;

  public Lender(String name, Rate rate, Amount availableAmount) {
    this.name = name;
    this.rate = rate;
    this.availableAmount = availableAmount;
  }

  public Rate getRate() {
    return rate;
  }

  public Amount getAvailableAmount() {
    return availableAmount;
  }

}
