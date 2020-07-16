package com.zopa.challenge.infrastructure.view.error;

import com.zopa.challenge.infrastructure.view.Console;

public class ErrorPrinter {
  private final Console console;

  public ErrorPrinter(Console console) {
    this.console = console;
  }

  public void print(Exception exception) {
    console.printLine(exception.getMessage());
  }
}
