package com.zopa.challenge.infrastructure.view.quote;

import com.zopa.challenge.infrastructure.view.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.zopa.challenge.builders.QuoteBuilder.aQuote;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QuotePrinterShould {

  @Mock
  private Console console;

  private QuotePrinter quotePrinter;

  @BeforeEach
  void setUp() {
    quotePrinter = new QuotePrinter(console);
  }

  @Test
  void print_quote() {
    quotePrinter.print(aQuote());

    InOrder inOrder = inOrder(console);
    inOrder.verify(console).printLine("Requested amount: £1000");
    inOrder.verify(console).printLine("Annual Interest Rate: 7.0%");
    inOrder.verify(console).printLine("Monthly repayment: £30.78");
    inOrder.verify(console).printLine("Total repayment: £1108.10");
  }

  @Test
  void print_empty_quote() {
    quotePrinter.printEmptyQuote();

    verify(console).printLine("The market does not have enough offers to fulfil the request, it is not possible to provide a quote");
  }
}