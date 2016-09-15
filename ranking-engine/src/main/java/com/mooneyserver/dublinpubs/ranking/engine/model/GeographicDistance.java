package com.mooneyserver.dublinpubs.ranking.engine.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GeographicDistance {

  private static final Double HALF_EARTH_CIRCUMFERENCE = 20_039D;
  private static final Double FEET_TO_METER = 0.3048;
  private static final Double KM_TO_METER = 1_000D;
  private static final Double MILE_TO_METER = 1_609.34;

  public static final GeographicDistance ALL_EARTH = GeographicDistance.builder()
      .distance(HALF_EARTH_CIRCUMFERENCE)
      .unitOfDistance(UnitOfDistance.KILOMETER)
      .build();

  public static enum UnitOfDistance {
    METERS    (1D),
    FEET      (FEET_TO_METER),
    KILOMETER (KM_TO_METER),
    MILES     (MILE_TO_METER);

    private Double toMeterMultiplier;

    UnitOfDistance(Double toMeterMultiplier) {
      this.toMeterMultiplier = toMeterMultiplier;
    }
  }

  private Double distance;
  private UnitOfDistance unitOfDistance;

  public Integer distanceInMeters() {
    return -1;
  }
}
