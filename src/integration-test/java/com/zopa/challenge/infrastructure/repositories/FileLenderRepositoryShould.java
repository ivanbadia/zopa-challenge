package com.zopa.challenge.infrastructure.repositories;

import com.zopa.challenge.domain.lender.Lender;
import com.zopa.challenge.domain.lender.LenderRepository;
import com.zopa.challenge.domain.shared.Amount;
import com.zopa.challenge.domain.shared.Rate;
import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;
import java.util.List;

import static com.zopa.challenge.MarketFilePath.pathOfMarketFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class FileLenderRepositoryShould {

  private static final List<Lender> EXPECTED_LENDERS = List.of(
      new Lender("Bob", new Rate(0.075), new Amount(640)),
      new Lender("Jane", new Rate(0.069), new Amount(480)),
      new Lender("Fred", new Rate(0.071), new Amount(520)),
      new Lender("Mary", new Rate(0.104), new Amount(170)),
      new Lender("John", new Rate(0.081), new Amount(320)),
      new Lender("Dave", new Rate(0.074), new Amount(140)),
      new Lender("Angela", new Rate(0.071), new Amount(60)));

  @Test
  public void retrieve_all_lenders() {
    LenderRepository lenderRepository = new FileLenderRepository(pathOfMarketFile().toString());
    List<Lender> lenders = lenderRepository.all();

    assertThat(lenders)
        .usingRecursiveFieldByFieldElementComparator()
        .isEqualTo(EXPECTED_LENDERS);
  }


  @Test
  public void fail_if_it_is_not_possible_to_read_the_file() {
    LenderRepository lenderRepository = new FileLenderRepository("non_existent_file.csv");

    Throwable throwable = catchThrowable(lenderRepository::all);

    assertThat(throwable)
        .isInstanceOf(RepositoryAccessException.class)
        .hasMessage("It was not possible to read the market file")
        .hasCauseInstanceOf(NoSuchFileException.class);
  }
}
