package com.mooneyserver.dublinpubs.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import org.slf4j.Logger;


import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MetricsService {

  private MetricRegistry metricRegistry;
  private Logger logger;

  @Inject
  public MetricsService(MetricRegistry metricRegistry, Logger logger) {
    this.metricRegistry = metricRegistry;
    this.logger = logger;
  }

  @PostConstruct
  public void initMetricsReporting() {
    logger.info("Instantiating Metrics Service now");
    Slf4jReporter.forRegistry(metricRegistry)
        .outputTo(logger)
        .convertRatesTo(TimeUnit.SECONDS)
        .convertDurationsTo(TimeUnit.MILLISECONDS)
        .build()
        .start(20, TimeUnit.SECONDS);
  }

  public TaskTimer timer(String name) {
    return new CodahaleTaskTimer(metricRegistry.getTimers().getOrDefault(name, metricRegistry.timer(name)));
  }

  public Map<String, Double> timerMetricsSnapshot() {
    Map<String, Double> currentMetrics = new HashMap<>();
    metricRegistry
        .getTimers()
        .forEach((name, timer) -> currentMetrics.put(name, timer.getSnapshot().getMean()));

    return currentMetrics;
  }
}
