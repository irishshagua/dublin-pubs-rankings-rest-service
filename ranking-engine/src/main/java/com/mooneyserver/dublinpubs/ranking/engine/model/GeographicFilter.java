package com.mooneyserver.dublinpubs.ranking.engine.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GeographicFilter {

  public static final GeographicFilter EARTH = GeographicFilter.builder()
      .geographicLocation(GeographicLocation.COOLEST_PLACE_ON_EARTH)
      .distance(GeographicDistance.ALL_EARTH)
      .build();

  private GeographicLocation geographicLocation;
  private GeographicDistance distance;
}
