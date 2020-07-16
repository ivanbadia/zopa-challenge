package com.zopa.challenge.application.usecases;

import com.zopa.challenge.domain.lender.Lender;
import com.zopa.challenge.domain.lender.LenderRepository;
import com.zopa.challenge.domain.quote.Quote;
import com.zopa.challenge.domain.quote.QuoteCalculator;
import com.zopa.challenge.domain.quote.RequestedAmount;
import com.zopa.challenge.domain.shared.Amount;
import com.zopa.challenge.domain.shared.Rate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.zopa.challenge.builders.QuoteBuilder.aQuote;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CalculateQuoteShould {

  private static final List<Lender> LENDERS = List.of(
      new Lender("Bob", new Rate(0.075), new Amount(640)),
      new Lender("Jane", new Rate(0.069), new Amount(480))
  );

  @Mock
  private LenderRepository lenderRepository;
  @Mock
  private QuoteCalculator quoteCalculator;

  private CalculateQuote calculateQuote;

  @BeforeEach
  void setUp() {
    calculateQuote = new CalculateQuote(lenderRepository, quoteCalculator);
  }

  @Test
  void calculate_quote() {
    RequestedAmount requestedAmount = new RequestedAmount(1000);
    given(lenderRepository.all()).willReturn(LENDERS);
    Quote expectedQuote = aQuote();
    given(quoteCalculator.calculateQuote(requestedAmount, LENDERS))
        .willReturn(Optional.of(expectedQuote));

    Optional<Quote> calculatedQuote = calculateQuote.execute(requestedAmount);

    assertThat(calculatedQuote)
        .isPresent()
        .contains(expectedQuote);
  }

}