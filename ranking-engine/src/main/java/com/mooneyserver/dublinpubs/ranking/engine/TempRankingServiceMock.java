package com.mooneyserver.dublinpubs.ranking.engine;

import com.mooneyserver.dublinpubs.ranking.engine.model.Criteria;
import com.mooneyserver.dublinpubs.ranking.engine.model.GeographicLocation;
import com.mooneyserver.dublinpubs.ranking.engine.model.Pub;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collection;

@ApplicationScoped
public class TempRankingServiceMock implements RankingService {

  @Override
  public Collection<Pub> mostPopularPubs(Criteria filter) {
    return new ArrayList<Pub>() {{
      add(new Pub("Some Boozer", new GeographicLocation(1D, -2D), 78.45, 4.1));
      add(new Pub("The Drop Inn", new GeographicLocation(2D, -3D), 72.16, 3.5));
      add(new Pub("The Crafty Cradle", new GeographicLocation(3D, -4D), 67.01, 3.6));
      add(new Pub("O'Donovans", new GeographicLocation(4D, -5D), 65.00, 2.5));
    }};
  }
}
