package com.zopa.challenge.infrastructure.view.error;

import com.zopa.challenge.domain.quote.InvalidRequestedAmountException;
import com.zopa.challenge.infrastructure.view.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ErrorPrinterShould {
  @Mock
  private Console console;

  private ErrorPrinter errorPrinter;

  @BeforeEach
  void setUp() {
    errorPrinter = new ErrorPrinter(console);
  }

  @Test
  void print_error_message() {
    String message = "The amount must be a numeric value";

    errorPrinter.print(new InvalidRequestedAmountException(message));

    verify(console).printLine(message);
  }

}