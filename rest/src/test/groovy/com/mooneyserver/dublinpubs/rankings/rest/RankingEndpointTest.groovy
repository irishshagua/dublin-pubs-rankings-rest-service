package com.mooneyserver.dublinpubs.rankings.rest

import com.mooneyserver.dublinpubs.ranking.engine.RankingService
import com.mooneyserver.dublinpubs.ranking.engine.model.Criteria
import spock.lang.Specification

class RankingEndpointTest extends Specification {

  def "The top count call should create a Criteria with the correct depth limit but no other filters"() {
    given:
      def rankingService = Mock(RankingService)
      def rankingEndpoint = new RankingEndpoint(rankingService)

    when:
      rankingEndpoint.getMostPopularPubs(5)

    then:
      1 * rankingService.retrievePubs(Criteria.builder().numPubsToReturn(5).build())
  }
}
