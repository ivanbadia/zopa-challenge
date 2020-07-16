package com.zopa.challenge.domain.quote;

import com.zopa.challenge.domain.shared.Rate;

public class Quote {
  private final RequestedAmount requestedAmount;
  private final Rate annualRate;
  private final Repayment monthlyRepayment;
  private final Repayment totalRepayment;

  public Quote(RequestedAmount requestedAmount, Rate annualRate, Repayment monthlyRepayment, Repayment totalRepayment) {
    this.requestedAmount = requestedAmount;
    this.annualRate = annualRate;
    this.monthlyRepayment = monthlyRepayment;
    this.totalRepayment = totalRepayment;
  }

  public RequestedAmount getRequestedAmount() {
    return requestedAmount;
  }

  public Rate getAnnualRate() {
    return annualRate;
  }

  public Repayment getMonthlyRepayment() {
    return monthlyRepayment;
  }

  public Repayment getTotalRepayment() {
    return totalRepayment;
  }
}
