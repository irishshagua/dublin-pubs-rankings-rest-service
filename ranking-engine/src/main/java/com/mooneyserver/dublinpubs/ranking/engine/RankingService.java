package com.mooneyserver.dublinpubs.ranking.engine;

import com.mooneyserver.dublinpubs.ranking.engine.model.Criteria;
import com.mooneyserver.dublinpubs.ranking.engine.model.Pub;

import java.util.Collection;

/**
 * A Service to retrieve {@link com.mooneyserver.dublinpubs.ranking.engine.model.Pub}'s
 * from an underlying data store based on some supplied search Criteria.
 */
public interface RankingService {

  /**
   * Retrieve Pub models from the underlying data store.
   *
   * @param filter A Search Criteria which will be applied to the retrieval
   *               operation from the underlying data store to filter the returned results
   * @return Collection of type Pub
   */
  Collection<Pub> retrievePubs(Criteria filter);
}
