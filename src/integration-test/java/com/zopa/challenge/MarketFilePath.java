package com.zopa.challenge;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MarketFilePath {

  private static final String MARKET_FILE = "market.csv";

  public static Path pathOfMarketFile() {
    try {
      return Paths.get(uriOf(MARKET_FILE));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static URI uriOf(String name) throws Exception {
    return MarketFilePath.class.getClassLoader().getResource(name).toURI();
  }
}
