package com.zopa.challenge.domain.shared;

import java.util.Objects;

public class Amount {
  private final int value;

  public Amount(int value) {
    this.value = value;
  }

  public static Amount minimum(Amount amount, Amount anotherAmount) {
    return new Amount(Math.min(amount.value, anotherAmount.value));
  }

  public int asInt() {
    return value;
  }

  public Amount subtract(Amount amount) {
    return new Amount(this.value - amount.asInt());
  }

  public boolean isZero() {
    return this.value == 0;
  }

  public boolean isGreaterThan(Amount amount) {
    return this.asInt() > amount.asInt();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Amount amount = (Amount) o;
    return value == amount.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
