package com.mooneyserver.dublinpubs.aop.performance

import com.mooneyserver.dublinpubs.metrics.MetricsService
import com.mooneyserver.dublinpubs.metrics.TaskTimer
import spock.lang.Specification

import javax.interceptor.InvocationContext

class PerformanceMonitoredInterceptorTest extends Specification {

  def "Timer should start and stop around normal invocations of a method"() {
    given:
      def metricsService = Mock(MetricsService)
      def invocationCtxt = Mock(InvocationContext)
      def timer = Mock(TaskTimer)
      def method = this.getClass().getMethods()[0] // Mocking of Method not possible
      def perfMonitor = new PerformanceMonitoredInterceptor(metricsService)

      metricsService.timer(_) >> timer
      invocationCtxt.getMethod() >> method

    when:
      perfMonitor.recordMethodDuration(invocationCtxt)

    then:
      1 * timer.start()
      1 * timer.stop()
  }

  def "Timer should start and stop around invocations of a method with throw an exception"() {
    given:
      def metricsService = Mock(MetricsService)
      def invocationCtxt = Mock(InvocationContext)
      def timer = Mock(TaskTimer)
      def method = this.getClass().getMethods()[0] // Mocking of Method not possible
      def perfMonitor = new PerformanceMonitoredInterceptor(metricsService)

      metricsService.timer(_) >> timer
      invocationCtxt.getMethod() >> method
      invocationCtxt.proceed() >> {new Exception()}

    when:
      perfMonitor.recordMethodDuration(invocationCtxt)

    then:
      1 * timer.start()
      1 * timer.stop()
  }
}
