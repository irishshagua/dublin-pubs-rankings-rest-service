package com.mooneyserver.dublinpubs.ranking.engine.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Builder
@Data
public class GeographicDistance {

  private static final Double HALF_EARTH_CIRCUMFERENCE = 20_039D;

  public static final GeographicDistance ALL_EARTH = GeographicDistance.builder()
      .distance(HALF_EARTH_CIRCUMFERENCE)
      .unitOfDistance(UnitOfDistance.KILOMETER)
      .build();

  public static final MathContext TO_METRE_ROUNDING = new MathContext(4, RoundingMode.FLOOR);

  public enum UnitOfDistance {
    METERS    (1D),
    FEET      (0.3048),
    KILOMETER (1_000D),
    MILES     (1_609.34);

    private final Double toMeterMultiplier;

    UnitOfDistance(final Double toMeterMultiplierIn) {
      this.toMeterMultiplier = toMeterMultiplierIn;
    }

    public Double getToMeterMultiplier() {
      return this.toMeterMultiplier;
    }
  }

  private Double distance;
  private UnitOfDistance unitOfDistance;

  /**
   * Convert any Geographic Distance to the equivalent
   * distance in the base distance type of meters.
   */
  public final BigDecimal distanceInMeters() {
    return new BigDecimal(
        distance * unitOfDistance.getToMeterMultiplier(),
        TO_METRE_ROUNDING
    );
  }
}
