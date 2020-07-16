package com.zopa.challenge.acceptance;

import com.zopa.challenge.LoanApplication;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public final class LoanConsole {

  private final ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();

  public void executeWith(String arguments) {
    replaceSystemOutputWith(consoleOutput);
    LoanApplication.main(arguments.split(" "));
    restoreSystemOutput();
  }

  public String readOutput() {
    return consoleOutput.toString();
  }

  private void restoreSystemOutput() {
    System.setOut(System.out);
  }

  private void replaceSystemOutputWith(ByteArrayOutputStream consoleOutput) {
    PrintStream printStream = new PrintStream(consoleOutput);
    System.setOut(printStream);
  }
}
