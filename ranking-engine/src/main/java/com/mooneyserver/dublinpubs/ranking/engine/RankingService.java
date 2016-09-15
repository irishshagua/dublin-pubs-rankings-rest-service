package com.mooneyserver.dublinpubs.ranking.engine;

import com.mooneyserver.dublinpubs.ranking.engine.model.Criteria;
import com.mooneyserver.dublinpubs.ranking.engine.model.Pub;

import java.util.Collection;

public interface RankingService {

  Collection<Pub> mostPopularPubs(Criteria filter);
}