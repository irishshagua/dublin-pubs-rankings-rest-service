package com.mooneyserver.dublinpubs.aop.performance;

import com.mooneyserver.dublinpubs.metrics.MetricsService;
import com.mooneyserver.dublinpubs.metrics.TaskTimer;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Class to be used in an AOP style to record the duration of code execution.
 * This class is to be used as an Interceptor and should not be manually created.
 */
@Interceptor
@PerformanceMonitored
public class PerformanceMonitoredInterceptor {

  private MetricsService metricsService;

  @Inject
  public PerformanceMonitoredInterceptor(MetricsService metricsService) {
    this.metricsService = metricsService;
  }

  /**
   * Surround the execution of a code block with a metric timer which will record the duration of
   * the method in question.
   */
  @AroundInvoke
  public Object recordMethodDuration(InvocationContext invocationContext) throws Exception {
    TaskTimer timer = metricsService.timer(invocationContext.getMethod().getName());
    try {
      timer.start();
      return invocationContext.proceed();
    } finally {
      timer.stop();
    }
  }
}
