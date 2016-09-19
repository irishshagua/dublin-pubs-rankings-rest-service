package com.mooneyserver.dublinpubs.rankings.rest;

import com.mooneyserver.dublinpubs.aop.performance.PerformanceMonitored;
import com.mooneyserver.dublinpubs.metrics.MetricsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.wildfly.swarm.monitor.Health;
import org.wildfly.swarm.monitor.HealthStatus;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Api(tags = {"unauthenticated", "health"})
@Path("/health")
@Produces(MediaType.APPLICATION_JSON)
public class HealthEndpoint {

  private MetricsService metricsService;

  public HealthEndpoint() {}

  @Inject
  public HealthEndpoint(MetricsService metricsService) {
    this.metricsService = metricsService;
  }

  @GET
  @Path("/")
  @PerformanceMonitored
  @Health
  @ApiOperation(
      value = "View the service health. Included is a breakdown of the current values for all the health metrics",
      notes = "200 means healthy, anything else is unhealthy")
  public HealthStatus performHealthCheck() {
    return healthOfService();
  }

  HealthStatus healthOfService() {
    Map<String, Double> metrics = metricsService.timerMetricsSnapshot();
    HealthStatus status = metrics.values()
        .stream()
        .anyMatch(methodDuration -> methodDuration > 250)
          ? HealthStatus.down()
          : HealthStatus.up();

    for (Map.Entry<String, Double> entry : metrics.entrySet()) {
      status = status.withAttribute(entry.getKey(), String.valueOf(entry.getValue()));
    }

    return status;
  }
}
