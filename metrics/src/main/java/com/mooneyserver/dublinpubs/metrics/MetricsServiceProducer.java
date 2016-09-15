package com.mooneyserver.dublinpubs.metrics;

import com.codahale.metrics.MetricRegistry;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@ApplicationScoped
public class MetricsServiceProducer {

  @Produces @Singleton
  public MetricsService metricsService(MetricRegistry metricRegistry, Logger logger) {
    MetricsService metricsService = new MetricsService(metricRegistry, logger);
    metricsService.initMetricsReporting();
    return metricsService;
  }

  @Produces @Singleton
  public MetricRegistry metricRegistry() {
    return new MetricRegistry();
  }
}
