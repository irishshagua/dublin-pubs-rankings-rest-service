package com.mooneyserver.dublinpubs.metrics

import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.Timer
import org.slf4j.Logger
import spock.lang.Specification

class MetricsServiceTest extends Specification {

  def "A timer for a new name will create a timer from the metrics registry"() {
    given:
      def metricsRegistry = Mock(MetricRegistry)
      def logger = Mock(Logger)
      def metricsService = new MetricsService(metricsRegistry, logger)

      metricsRegistry.getTimers() >> new TreeMap<String, Timer>()

    when:
      metricsService.timer("New Name")

    then:
      1 * metricsRegistry.timer("New Name")
  }

  def "A timer for an existing name will be pulled from the cache"() {
    given:
      def metricsRegistry = Mock(MetricRegistry)
      def logger = Mock(Logger)
      def timer = Mock(Timer)
      def metricsService = new MetricsService(metricsRegistry, logger)

    when:
      metricsService.timer("New Name")

    then:
      1 * metricsRegistry.getTimers() >> new TreeMap<String, Timer>() {{
        put("New Name", timer)
      }}
      0 * metricsRegistry.timer("New Name")
  }

  def "Mean time for all current metrics is accessible"() {
    given:
      def metricsRegistry = new MetricRegistry()
      def logger = Mock(Logger)
      def metricsService = new MetricsService(metricsRegistry, logger)

      def timer1 = metricsService.timer("Timer 1")
      def timer2 = metricsService.timer("Timer 2")

    when:
      timer1.start()
      timer1.stop()
      timer2.start()
      Thread.sleep(50)
      timer2.stop()

      def details = metricsService.timerMetricsSnapshot()

    then:
      details.size() == 2
      details.get("Timer 1") < details.get("Timer 2")
  }
}
