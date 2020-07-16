package com.zopa.challenge.infrastructure.view.quote;

import com.zopa.challenge.domain.quote.Quote;
import com.zopa.challenge.infrastructure.view.Console;

public class QuotePrinter {
  private static final String NOT_ENOUGH_OFFERS_TO_PROVIDE_A_QUOTE = "The market does not have enough offers to fulfil the request, " +
      "it is not possible to provide a quote";

  private final Console console;

  public QuotePrinter(Console console) {
    this.console = console;
  }

  public void print(Quote quote) {
    console.printLine(String.format("Requested amount: £%d", quote.getRequestedAmount().asInt()));
    console.printLine(String.format("Annual Interest Rate: %.1f%%", quote.getAnnualRate().asDouble() * 100));
    console.printLine(String.format("Monthly repayment: £%.2f", quote.getMonthlyRepayment().asDouble()));
    console.printLine(String.format("Total repayment: £%.2f", quote.getTotalRepayment().asDouble()));
  }

  public void printEmptyQuote() {
    console.printLine(NOT_ENOUGH_OFFERS_TO_PROVIDE_A_QUOTE);
  }
}
