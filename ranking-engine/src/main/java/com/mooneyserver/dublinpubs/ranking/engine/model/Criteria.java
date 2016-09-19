package com.mooneyserver.dublinpubs.ranking.engine.model;

import lombok.Builder;
import lombok.Data;

/**
 * The search Criteria to be used for retrieving
 * {@link com.mooneyserver.dublinpubs.ranking.engine.model.Pub}
 * models from the underlying data store.
 */
@Builder
@Data
public class Criteria {

  /**
   * Integer constant to avoid magic numbers in the default
   * Criteria exposed by this class.
   */
  private static final Integer TOP_TEN_COUNT = 10;

  /**
   * Default search criteria to return the 10 most popular
   * pubs in the underlying data store based on their ratings.
   */
  public static final Criteria TOP_TEN = Criteria.builder()
      .numPubsToReturn(TOP_TEN_COUNT)
      .searchArea(GeographicFilter.EARTH)
      .ratingsFilter(RatingsFilter.NO_FILTER)
      .resultsOrder(Order.RATINGS_DESCENDING)
      .build();

  /**
   * An Order which can be applied to the Search Criteria
   * to assign precedence to which Pubs are returned deom
   * the query when there are more pubs than the data limit
   * allows.
   */
  public enum Order {
    /** Favour Pubs which are closer to centre of the requested search area. */
    PROXIMITY_NEAREST,
    /**
     * Favour Pubs which are farther from the centre of the
     * requested search area.
     */
    PROXIMITY_FARTHEST,
    /** Favour Pubs which have a lower rating. */
    RATINGS_ASCENDING,
    /** Favour Pubs which have a higher rating. */
    RATINGS_DESCENDING;
  }

  /**
   * A limit for the number of
   * {@link com.mooneyserver.dublinpubs.ranking.engine.model.Pub}'s
   * which should be returned by any data store retrieval.
   */
  private Integer numPubsToReturn;
  /**
   * Any {@link com.mooneyserver.dublinpubs.ranking.engine.model.Pub}'s
   * returned by the data store retrieval should be located
   * within this search area.
   */
  private GeographicFilter searchArea;
  /**
   * Any {@link com.mooneyserver.dublinpubs.ranking.engine.model.Pub}'s
   * returned by the data store retrieval should have a rating
   * which falls within the bounds of this ratings filter.
   */
  private RatingsFilter ratingsFilter;
  /**
   * A preference order to be applied to the
   * {@link com.mooneyserver.dublinpubs.ranking.engine.model.Pub}'s returned
   * by the data store retrieval should there be more pubs than the allowed
   * {@link com.mooneyserver.dublinpubs.ranking.engine.model.Criteria#numPubsToReturn}.
   */
  private Order resultsOrder;
}
