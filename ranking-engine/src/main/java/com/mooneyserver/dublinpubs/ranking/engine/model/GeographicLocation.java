package com.mooneyserver.dublinpubs.ranking.engine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class GeographicLocation {

  private static final Double COOLEST_PLACE_ON_EARTH_LATITUDE = 53.344217;
  private static final Double COOLEST_PLACE_ON_EARTH_LONGITUDE = -6.556261;

  public static final GeographicLocation COOLEST_PLACE_ON_EARTH = GeographicLocation.builder()
      .latitude(COOLEST_PLACE_ON_EARTH_LATITUDE)
      .longitude(COOLEST_PLACE_ON_EARTH_LONGITUDE)
      .build();

  private Double latitude;
  private Double longitude;
}
