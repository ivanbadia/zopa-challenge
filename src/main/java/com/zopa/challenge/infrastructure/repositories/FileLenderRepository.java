package com.zopa.challenge.infrastructure.repositories;

import com.zopa.challenge.domain.shared.Amount;
import com.zopa.challenge.domain.lender.Lender;
import com.zopa.challenge.domain.lender.LenderRepository;
import com.zopa.challenge.domain.shared.Rate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileLenderRepository implements LenderRepository {
  private final String file;

  private static final int NAME_POSITION = 0;
  private static final int RATE_POSITION = 1;
  private static final int AVAILABLE_AMOUNT_POSITION = 2;

  public FileLenderRepository(String file) {
    this.file = file;
  }

  private final Function<String, Lender> parseLender  = line -> {
    List<String> lenderValues = Arrays.stream(line.split(","))
        .map(String::trim)
        .collect(Collectors.toList());
    return new Lender(lenderValues.get(NAME_POSITION),
        new Rate(Double.parseDouble(lenderValues.get(RATE_POSITION))),
        new Amount(Integer.parseInt(lenderValues.get(AVAILABLE_AMOUNT_POSITION))));
  };

  @Override
  public List<Lender> all() {
    try (Stream<String> stream = Files.lines(Paths.get(file)).skip(1)) {
      return stream.map(parseLender)
          .collect(Collectors.toList());
    } catch (Exception e) {
      throw new RepositoryAccessException("It was not possible to read the market file", e);
    }
  }

}
