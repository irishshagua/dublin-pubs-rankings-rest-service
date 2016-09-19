package com.mooneyserver.dublinpubs.ranking.engine.model

import com.mooneyserver.dublinpubs.ranking.engine.model.GeographicDistance.UnitOfDistance
import spock.lang.Specification

import static com.mooneyserver.dublinpubs.ranking.engine.model.GeographicDistance.TO_METRE_ROUNDING;

class GeographicDistanceTest extends Specification {

  def "Any unit of distance can be converted to meters"() {
    expect:
      a.distanceInMeters() == c

    where:
      a || c
      GeographicDistance.builder().distance(10457).unitOfDistance(UnitOfDistance.MILES).build() || new BigDecimal(16_828_910, TO_METRE_ROUNDING)
      GeographicDistance.builder().distance(127).unitOfDistance(UnitOfDistance.KILOMETER).build() || new BigDecimal(127_000, TO_METRE_ROUNDING)
      GeographicDistance.builder().distance(12).unitOfDistance(UnitOfDistance.FEET).build() || new BigDecimal(3.6576, TO_METRE_ROUNDING)
      GeographicDistance.builder().distance(15).unitOfDistance(UnitOfDistance.METERS).build() || new BigDecimal(15, TO_METRE_ROUNDING)
  }
}
