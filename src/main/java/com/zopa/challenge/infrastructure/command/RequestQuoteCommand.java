package com.zopa.challenge.infrastructure.command;

import com.zopa.challenge.application.usecases.CalculateQuote;
import com.zopa.challenge.domain.quote.InvalidRequestedAmountException;
import com.zopa.challenge.domain.quote.RequestedAmount;
import com.zopa.challenge.infrastructure.repositories.RepositoryAccessException;
import com.zopa.challenge.infrastructure.view.error.ErrorPrinter;
import com.zopa.challenge.infrastructure.view.quote.QuotePrinter;

public class RequestQuoteCommand {
  private final CalculateQuote calculateQuote;
  private final QuotePrinter quotePrinter;
  private final ErrorPrinter errorPrinter;

  public RequestQuoteCommand(CalculateQuote calculateQuote, QuotePrinter quotePrinter, ErrorPrinter errorPrinter) {
    this.calculateQuote = calculateQuote;
    this.quotePrinter = quotePrinter;
    this.errorPrinter = errorPrinter;
  }

  public void calculateQuoteFor(String amount) {
    try {
      calculateQuote.execute(RequestedAmount.valueOf(amount))
          .ifPresentOrElse(quotePrinter::print, quotePrinter::printEmptyQuote);
    } catch (RepositoryAccessException | InvalidRequestedAmountException e) {
      errorPrinter.print(e);
    }
  }
}
