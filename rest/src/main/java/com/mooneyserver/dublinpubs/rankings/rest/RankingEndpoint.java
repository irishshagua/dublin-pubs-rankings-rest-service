package com.mooneyserver.dublinpubs.rankings.rest;

import com.mooneyserver.dublinpubs.aop.performance.PerformanceMonitored;
import com.mooneyserver.dublinpubs.aop.web.WebResponse;
import com.mooneyserver.dublinpubs.aop.web.model.ResponseWrapper;
import com.mooneyserver.dublinpubs.ranking.engine.model.Criteria;
import com.mooneyserver.dublinpubs.ranking.engine.RankingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Api(
    value = "/ranking",
    produces = MediaType.APPLICATION_JSON,
    tags = {"unauthenticated", "ranking", "popularity"})
@Path("/ranking")
@Produces(MediaType.APPLICATION_JSON)
public class RankingEndpoint {

  private static final int MIN_POPULARITY_COUNT = 1;
  private static final int MAX_POPULARITY_COUNT = 25;

  private RankingService rankingService;

  public RankingEndpoint() {}

  @Inject
  public RankingEndpoint(RankingService rankingService) {
    this.rankingService = rankingService;
  }

  @GET
  @Path("/top/{count}")
  @PerformanceMonitored
  @ApiOperation(value = "Return a defined number of the most popular pubs based on overall review scores",
      notes = "This has no filtering on minimum ratings or geographic location",
      response = ResponseWrapper.class,
      httpMethod = "GET")
  @WebResponse
  public Object getMostPopularPubs(
      @NotNull(message = "{rest.validation.popular.null}")
      @Min(value = MIN_POPULARITY_COUNT, message = "{rest.validation.popular.min}")
      @Max(value = MAX_POPULARITY_COUNT, message = "{rest.validation.popular.max}")
      @PathParam("count") Integer popularityDepth) {
    return rankingService.retrievePubs(Criteria.builder().numPubsToReturn(popularityDepth).build());
  }
}
