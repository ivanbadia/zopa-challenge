package com.zopa.challenge.domain.quote;

import com.zopa.challenge.domain.shared.Amount;
import com.zopa.challenge.domain.lender.Lender;
import com.zopa.challenge.domain.shared.Rate;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class QuoteCalculatorShould {
  private static final List<Lender> LENDERS = List.of(
      new Lender("Bob", new Rate(0.075), new Amount(640)),
      new Lender("Jane", new Rate(0.069), new Amount(480)),
      new Lender("Fred", new Rate(0.071), new Amount(540)),
      new Lender("Mary", new Rate(0.104), new Amount(170)),
      new Lender("John", new Rate(0.081), new Amount(320)),
      new Lender("Dave", new Rate(0.074), new Amount(140)),
      new Lender("Angela", new Rate(0.071), new Amount(60)));
  private static final int NUMBER_OF_PAYMENTS = 36;
  private static final RequestedAmount AMOUNT_GREATER_THAN_AVAILABLE_AMOUNT = new RequestedAmount(15000);

  private final QuoteCalculator quoteCalculator = new QuoteCalculator(NUMBER_OF_PAYMENTS);

  @Test
  void calculate_quote() {
    RequestedAmount requestedAmount = new RequestedAmount(1000);

    Optional<Quote> quote = quoteCalculator.calculateQuote(requestedAmount, LENDERS);

    assertThat(quote)
        .isPresent();
    assertThat(quote.get())
        .usingRecursiveComparison()
        .isEqualTo(new Quote(requestedAmount,
            new Rate(0.07),
            new Repayment(30.78),
            new Repayment(1108.10)));
  }

  @Test
  void provide_an_empty_quote_when_there_are_not_enough_offers() {
    Optional<Quote> quote = quoteCalculator.calculateQuote(AMOUNT_GREATER_THAN_AVAILABLE_AMOUNT, LENDERS);

    assertThat(quote)
        .isEmpty();
  }

}