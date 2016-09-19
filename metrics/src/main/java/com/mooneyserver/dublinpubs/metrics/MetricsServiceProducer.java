package com.mooneyserver.dublinpubs.metrics;

import com.codahale.metrics.MetricRegistry;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

/**
 * A CDI class to produce the MetricsService and MetricRegistry to avoid
 * building these classes by hand.
 */
@ApplicationScoped
public class MetricsServiceProducer {

  /**
   * Create and initialise a Metrics Service for use for the duration of the
   * applications runtime so that all metrics are managed by a single entity.
   */
  @Produces
  @Singleton
  public MetricsService metricsService(MetricRegistry metricRegistry, Logger logger) {
    MetricsService metricsService = new MetricsService(metricRegistry, logger);
    metricsService.initMetricsReporting();
    return metricsService;
  }

  /**
   * Produce a single metric registry so that no duplicate metrics can
   * be created.
   */
  @Produces
  @Singleton
  public MetricRegistry metricRegistry() {
    return new MetricRegistry();
  }
}
