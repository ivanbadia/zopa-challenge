package com.zopa.challenge.builders;

import com.zopa.challenge.domain.quote.Quote;
import com.zopa.challenge.domain.quote.Repayment;
import com.zopa.challenge.domain.quote.RequestedAmount;
import com.zopa.challenge.domain.shared.Rate;

public class QuoteBuilder {
  private QuoteBuilder() {
  }

  public static Quote aQuote() {
    return new Quote(new RequestedAmount(1000),
        new Rate(0.07),
        new Repayment(30.78),
        new Repayment(1108.10));
  }
}
