package com.zopa.challenge.acceptance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.zopa.challenge.MarketFilePath.pathOfMarketFile;
import static java.text.MessageFormat.format;
import static org.assertj.core.api.Assertions.assertThat;

class CalculateQuoteFeature {

  private static final String LOAN_AMOUNT = "1000";
  private static final String EXPECTED_QUOTE = "Requested amount: £1000\n" +
      "Annual Interest Rate: 7.0%\n" +
      "Monthly repayment: £30.78\n" +
      "Total repayment: £1108.10\n";

  private LoanConsole loanConsole;

  private Path inputFile;

  @BeforeEach
  void start() {
    loanConsole = new LoanConsole();
    inputFile = pathOfMarketFile();
  }

  @Test
  void should_provide_quote_with_the_lowest_rate() {
    whenTheApplicationIsExecutedWith(parameters(inputFile, LOAN_AMOUNT));
    thenTheOutputIsEqualTo(EXPECTED_QUOTE);
  }

  @Test
  void should_fail_when_required_parameters_are_not_informed() {
    whenTheApplicationIsExecutedWith(parameter(inputFile));
    thenTheOutputIsEqualTo("Parameters [marked_file_path] [loan_amount] are required\n");
  }

  @Test
  void should_fail_when_requested_amount_is_not_a_numeric_value() {
    whenTheApplicationIsExecutedWith(parameters(inputFile, "invalid_amount"));
    thenTheOutputIsEqualTo("The amount must be a numeric value\n");
  }

  @Test
  void should_fail_when_requested_amount_is_not_valid() {
    whenTheApplicationIsExecutedWith(parameters(inputFile, "110"));
    thenTheOutputIsEqualTo("The requested amount must be inform in any £100 increment\n");
  }

  @Test
  void should_fail_when_it_is_not_possible_to_provide_a_quote() {
    whenTheApplicationIsExecutedWith(parameters(inputFile, "15000"));
    thenTheOutputIsEqualTo("The market does not have enough offers to fulfil the request, it is not possible to provide a quote\n");
  }

  @Test
  void should_fail_when_markets_file_is_not_found() {
    whenTheApplicationIsExecutedWith(parameters(Paths.get("not_valid_file"), "1000"));
    thenTheOutputIsEqualTo("It was not possible to read the market file\n");
  }

  private void whenTheApplicationIsExecutedWith(String format) {
    loanConsole.executeWith(format);
  }

  private void thenTheOutputIsEqualTo(String expectedOutput) {
    String output = loanConsole.readOutput();
    assertThat(output)
        .isEqualTo(expectedOutput);
  }

  private String parameters(Path inputFile, String loanAmount) {
    return format("{0} {1}", inputFile, loanAmount);
  }

  private String parameter(Path inputFile) {
    return format("{0}", inputFile);
  }
}
