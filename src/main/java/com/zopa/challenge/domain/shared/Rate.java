package com.zopa.challenge.domain.shared;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rate {
  private static final int RATE_SCALE = 3;
  private final double value;

  public Rate(double value) {
    this.value = BigDecimal.valueOf(value)
        .setScale(RATE_SCALE, RoundingMode.HALF_UP)
        .doubleValue();
  }

  public double asDouble() {
    return value;
  }

  public double applyTo(Amount borrowedAmount) {
    return borrowedAmount.asInt() * value;
  }
}
