package com.mooneyserver.dublinpubs.ranking.engine;

import com.mooneyserver.dublinpubs.ranking.engine.model.Criteria;
import com.mooneyserver.dublinpubs.ranking.engine.model.GeographicLocation;
import com.mooneyserver.dublinpubs.ranking.engine.model.Pub;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A temporary mock implementation of the Ranking Service until
 * I have some kind of data store in place.
 */
@ApplicationScoped
public class TempRankingServiceMock implements RankingService {

  private static Collection<Pub> MOCK_DATA = new ArrayList<>(4);

  static {
    MOCK_DATA.add(new Pub("Some Boozer", new GeographicLocation(1D, -2D), 78.45, 4.1));
    MOCK_DATA.add(new Pub("The Drop Inn", new GeographicLocation(2D, -3D), 72.16, 3.5));
    MOCK_DATA.add(new Pub("The Crafty Cradle", new GeographicLocation(3D, -4D), 67.01, 3.6));
    MOCK_DATA.add(new Pub("O'Donovans", new GeographicLocation(4D, -5D), 65.00, 2.5));
  }

  @Override
  public Collection<Pub> retrievePubs(Criteria filter) {
    return MOCK_DATA;
  }
}
