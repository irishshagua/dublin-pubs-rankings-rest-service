package com.mooneyserver.dublinpubs.ranking.engine.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RatingsFilter {

  private static final Double ZERO_PERCENT = 0D;
  private static final Double ONE_HUNDRED_PERCENT = 100D;
  private static final Double ZERO_STAR_RATING = 0D;
  private static final Double MAX_FIVE_STAR_RATING = 5D;

  public static final RatingsFilter NO_FILTER = RatingsFilter.builder()
      .minimumInternalPercentageRating(ZERO_PERCENT)
      .maximumInternalPercentageRating(ONE_HUNDRED_PERCENT)
      .lowerBoundAverageUserStarsRating(ZERO_STAR_RATING)
      .upperBoundAverageUserStarsRating(MAX_FIVE_STAR_RATING)
      .build();

  private Double minimumInternalPercentageRating;
  private Double maximumInternalPercentageRating;
  private Double lowerBoundAverageUserStarsRating;
  private Double upperBoundAverageUserStarsRating;
}
