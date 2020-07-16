package com.zopa.challenge;

import com.zopa.challenge.application.usecases.CalculateQuote;
import com.zopa.challenge.domain.lender.LenderRepository;
import com.zopa.challenge.domain.quote.QuoteCalculator;
import com.zopa.challenge.infrastructure.command.RequestQuoteCommand;
import com.zopa.challenge.infrastructure.repositories.FileLenderRepository;
import com.zopa.challenge.infrastructure.view.Console;
import com.zopa.challenge.infrastructure.view.error.ErrorPrinter;
import com.zopa.challenge.infrastructure.view.quote.QuotePrinter;


public class LoanApplication {

  private static final int NUMBER_OF_PAYMENTS = 36;

  public static void main(String[] args) {
    Console console = new Console();
    if(args.length<2){
      console.printLine("Parameters [marked_file_path] [loan_amount] are required");
      return;
    }

    String file = args[0];
    LenderRepository lenderRepository = lenderRepository(file);
    RequestQuoteCommand requestQuoteCommand = new RequestQuoteCommand(calculateQuote(lenderRepository), quotePrinter(console), errorPrinter(console));
    String requestedAmount = args[1];

    requestQuoteCommand.calculateQuoteFor(requestedAmount);
  }

  private static ErrorPrinter errorPrinter(Console console) {
    return new ErrorPrinter(console);
  }

  private static QuotePrinter quotePrinter(Console console) {
    return new QuotePrinter(console);
  }

  private static CalculateQuote calculateQuote(LenderRepository lenderRepository) {
    return new CalculateQuote(lenderRepository, quoteCalculator());
  }

  private static QuoteCalculator quoteCalculator() {
    return new QuoteCalculator(NUMBER_OF_PAYMENTS);
  }

  private static LenderRepository lenderRepository(String file) {
    return new FileLenderRepository(file);
  }
}
