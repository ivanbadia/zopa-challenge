package com.zopa.challenge.domain.quote;

import com.zopa.challenge.domain.lender.Lender;
import com.zopa.challenge.domain.shared.Amount;
import com.zopa.challenge.domain.shared.Rate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class QuoteCalculator {
  private final int numberOfPayments;

  public QuoteCalculator(int numberOfPayments){
    this.numberOfPayments = numberOfPayments;
  }

  public Optional<Quote> calculateQuote(RequestedAmount requestedAmount, List<Lender> lenders) {
    if(requestedAmount.isGreaterThan(availableAmountFrom(lenders))){
      return Optional.empty();
    }

    double totalInterest = calculateTotalInterest(requestedAmount, lenders);
    double annualInterestRate = calculateAnnualInterestRate(requestedAmount, totalInterest);
    double monthlyPayment = calculateMonthlyPayment(requestedAmount, annualInterestRate);

    return Optional.of(new Quote(requestedAmount,
        new Rate(annualInterestRate),
        new Repayment(monthlyPayment),
        new Repayment(monthlyPayment * numberOfPayments)));
  }

  private Amount availableAmountFrom(List<Lender> lenders) {
    int availableAmount = lenders.stream()
        .map(Lender::getAvailableAmount)
        .mapToInt(Amount::asInt)
        .sum();
    return new Amount(availableAmount);
  }

  private double calculateTotalInterest(RequestedAmount requestedAmount, List<Lender> lenders) {
    List<Lender> orderedLendersByRate = sortByRate(lenders);

    double totalInterest = 0;
    Amount remainingAmount = requestedAmount;

    for (Lender lender : orderedLendersByRate) {
      if (remainingAmount.isZero()) {
        break;
      }
      Amount borrowedAmount = Amount.minimum(remainingAmount, lender.getAvailableAmount());
      double interestOfBorrowedAmount = lender.getRate().applyTo(borrowedAmount);
      totalInterest = totalInterest + interestOfBorrowedAmount;
      remainingAmount = remainingAmount.subtract(borrowedAmount);
    }

    return totalInterest;
  }

  private List<Lender> sortByRate(List<Lender> lenders) {
    List<Lender> orderedLendersByRate = new ArrayList<>(lenders);
    orderedLendersByRate.sort(Comparator.comparingDouble(lender -> lender.getRate().asDouble()));
    return orderedLendersByRate;
  }

  private double calculateAnnualInterestRate(RequestedAmount requestedAmount, double totalInterest) {
    return totalInterest / requestedAmount.asInt();
  }

  private double calculateMonthlyPayment(RequestedAmount requestedAmount, double annualInterestRate) {
    double monthlyInterestRate = Math.pow((1 + annualInterestRate), (1.0 / 12)) - 1;
    return (monthlyInterestRate * requestedAmount.asInt()) /
        (1 - (Math.pow(1 + monthlyInterestRate, -numberOfPayments)));
  }

}
