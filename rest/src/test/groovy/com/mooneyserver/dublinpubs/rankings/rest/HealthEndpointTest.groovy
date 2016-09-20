package com.mooneyserver.dublinpubs.rankings.rest

import com.mooneyserver.dublinpubs.metrics.MetricsService
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.wildfly.swarm.monitor.Status
import spock.lang.Specification

class HealthEndpointTest extends Specification {

  def "Health Endpoint should show as up if not metric is above the allowed threshold"() {
    given:
      def metricsService = Mock(MetricsService)
      def healthEndpoint = new HealthEndpoint(metricsService)

      metricsService.timerMetricsSnapshot() >> [metric1:220D, metric2:180D]

    when:
      def health = healthEndpoint.healthOfService()

    then:
      health.state == Status.State.UP
      JsonOutput.toJson(new JsonSlurper().parseText(health.message.get())) == JsonOutput.toJson([metric1:"220.0", metric2:"180.0"])
  }

  def "Health Endpoint should show as down if any metric is below the allowed threshold"() {
    given:
      def metricsService = Mock(MetricsService)
      def healthEndpoint = new HealthEndpoint(metricsService)

      metricsService.timerMetricsSnapshot() >> [metric1:220D, metric2:275D]

    when:
      def health = healthEndpoint.healthOfService()

    then:
      health.state == Status.State.DOWN
      JsonOutput.toJson(new JsonSlurper().parseText(health.message.get())) == JsonOutput.toJson([metric1:"220.0", metric2:"275.0"])
  }

  def "If there are no metrics, the health should show as up"() {
    given:
      def metricsService = Mock(MetricsService)
      def healthEndpoint = new HealthEndpoint(metricsService)

      metricsService.timerMetricsSnapshot() >> [:]

    when:
      def health = healthEndpoint.healthOfService()

    then:
      health.state == Status.State.UP
  }
}
