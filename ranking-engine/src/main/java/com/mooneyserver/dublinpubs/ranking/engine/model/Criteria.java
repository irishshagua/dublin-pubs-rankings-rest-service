package com.mooneyserver.dublinpubs.ranking.engine.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Criteria {

  private static final Integer TOP_TEN_COUNT = 10;

  public static final Criteria TOP_TEN = Criteria.builder()
      .numPubsToReturn(TOP_TEN_COUNT)
      .searchArea(GeographicFilter.EARTH)
      .ratingsFilter(RatingsFilter.NO_FILTER)
      .resultsOrder(Order.RATINGS_DESCENDING)
      .build();

  public static enum Order {
    PROXIMITY_NEAREST,
    PROXIMITY_FARTHEST,
    RATINGS_ASCENDING,
    RATINGS_DESCENDING;
  }

  private Integer numPubsToReturn;
  private GeographicFilter searchArea;
  private RatingsFilter ratingsFilter;
  private Order resultsOrder;
}
