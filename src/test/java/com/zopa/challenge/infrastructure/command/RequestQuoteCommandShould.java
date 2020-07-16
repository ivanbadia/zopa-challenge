package com.zopa.challenge.infrastructure.command;

import com.zopa.challenge.application.usecases.CalculateQuote;
import com.zopa.challenge.domain.quote.InvalidRequestedAmountException;
import com.zopa.challenge.domain.quote.Quote;
import com.zopa.challenge.domain.quote.RequestedAmount;
import com.zopa.challenge.infrastructure.repositories.RepositoryAccessException;
import com.zopa.challenge.infrastructure.view.error.ErrorPrinter;
import com.zopa.challenge.infrastructure.view.quote.QuotePrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.NoSuchFileException;
import java.util.Optional;

import static com.zopa.challenge.builders.QuoteBuilder.aQuote;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RequestQuoteCommandShould {

  @Mock
  private CalculateQuote calculateQuote;

  @Mock
  private QuotePrinter quotePrinter;

  @Mock
  private ErrorPrinter errorPrinter;

  private RequestQuoteCommand requestQuoteCommand;

  @BeforeEach
  void setUp() {
    requestQuoteCommand = new RequestQuoteCommand(calculateQuote, quotePrinter, errorPrinter);
  }


  @Test
  void print_quote() {
    Quote quote = aQuote();
    given(calculateQuote.execute(new RequestedAmount(1000))).willReturn(Optional.of(quote));

    requestQuoteCommand.calculateQuoteFor("1000");

    verify(quotePrinter).print(quote);
  }

  @Test
  void print_error_when_requested_amount_is_not_valid() {
    requestQuoteCommand.calculateQuoteFor("not_numeric_amount");

    verify(errorPrinter).print(any(InvalidRequestedAmountException.class));
  }

  @Test
  void print_message_when_it_is_not_possible_to_provide_a_quote() {
    given(calculateQuote.execute(any())).willReturn(Optional.empty());

    requestQuoteCommand.calculateQuoteFor("1000");

    verify(quotePrinter).printEmptyQuote();
  }

  @Test
  void print_error_when_file_cannot_be_read() {
    RepositoryAccessException exception = new RepositoryAccessException("Error", new NoSuchFileException("file"));
    given(calculateQuote.execute(any())).willThrow(exception);

    requestQuoteCommand.calculateQuoteFor("1000");

    verify(errorPrinter).print(exception);
  }
}