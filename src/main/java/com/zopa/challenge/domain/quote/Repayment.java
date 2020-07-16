package com.zopa.challenge.domain.quote;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Repayment {
  private static final int REPAYMENT_SCALE = 2;
  private final double value;

  public Repayment(double value) {
    this.value = BigDecimal.valueOf(value)
        .setScale(REPAYMENT_SCALE, RoundingMode.HALF_UP)
        .doubleValue();
  }

  public double asDouble() {
    return value;
  }
}
