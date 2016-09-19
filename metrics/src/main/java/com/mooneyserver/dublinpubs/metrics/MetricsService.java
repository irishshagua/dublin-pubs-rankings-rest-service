package com.mooneyserver.dublinpubs.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.Timer;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * The Metrics Service is responsible for providing timers
 * which can be used to track the duration of code execution
 * within the metrics library.
 */
public class MetricsService {

  private MetricRegistry metricRegistry;
  private Logger logger;

  /**
   * Instantiate the Metrics Service with a metrics
   * registry from Codahale which is responsible for managing
   * the created timers.
   *
   * @param metricRegistry Condahale Metrics Registry {@see http://metrics.dropwizard.io/3.1.0/getting-started/}
   * @param logger SLF4J logger
   */
  @Inject
  public MetricsService(MetricRegistry metricRegistry, Logger logger) {
    this.metricRegistry = metricRegistry;
    this.logger = logger;
  }

  /**
   * Start a poller which will output the current
   * metric values to the log every 20 seconds.
   */
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

  /**
   * Provide a one time use timer.
   *
   * @param name An identifier for the metric being recorded
   * @return An instance of the TaskTimer interface
   */
  public TaskTimer timer(String name) {
    Timer timer = Optional
        .ofNullable(metricRegistry.getTimers().get(name))
        .orElseGet(() -> metricRegistry.timer(name));

    return new CodahaleTaskTimer(timer);
  }

  /**
   * Get the current mean value for all the timers
   * which have been used so far in the current
   * runtime of this application.
   *
   * @return A Map representing metric name and mean duration
   */
  public Map<String, Double> timerMetricsSnapshot() {
    Map<String, Double> currentMetrics = new HashMap<>();
    metricRegistry
        .getTimers()
        .forEach((name, timer) -> currentMetrics.put(name, timer.getSnapshot().getMean()));

    return currentMetrics;
  }
}
