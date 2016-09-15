package com.mooneyserver.dublinpubs.ranking.engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pub {

  private String name;
  private GeographicLocation geoLocation;
  private Double averageFiveStarUserRating;
  private Double systemRating;
}
