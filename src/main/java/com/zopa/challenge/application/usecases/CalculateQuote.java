package com.zopa.challenge.application.usecases;

import com.zopa.challenge.domain.lender.Lender;
import com.zopa.challenge.domain.lender.LenderRepository;
import com.zopa.challenge.domain.quote.Quote;
import com.zopa.challenge.domain.quote.QuoteCalculator;
import com.zopa.challenge.domain.quote.RequestedAmount;

import java.util.List;
import java.util.Optional;

public class CalculateQuote {
  private final LenderRepository lenderRepository;
  private final QuoteCalculator quoteCalculator;

  public CalculateQuote(LenderRepository lenderRepository, QuoteCalculator quoteCalculator) {
    this.lenderRepository = lenderRepository;
    this.quoteCalculator = quoteCalculator;
  }

  public Optional<Quote> execute(RequestedAmount requestedAmount) {
    List<Lender> lenders = lenderRepository.all();
    return quoteCalculator.calculateQuote(requestedAmount, lenders);
  }
}
