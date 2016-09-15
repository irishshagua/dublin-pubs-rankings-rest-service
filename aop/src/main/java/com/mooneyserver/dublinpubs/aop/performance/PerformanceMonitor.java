package com.mooneyserver.dublinpubs.aop.performance;

import com.mooneyserver.dublinpubs.metrics.MetricsService;
import com.mooneyserver.dublinpubs.metrics.TaskTimer;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@PerformanceMonitored
public class PerformanceMonitor {

  private MetricsService metricsService;

  @Inject
  public PerformanceMonitor(MetricsService metricsService) {
    this.metricsService = metricsService;
  }

  @AroundInvoke
  public Object recordMethodDuration(InvocationContext invocationContext) throws Exception {
    TaskTimer timer = metricsService.timer(invocationContext.getMethod().getName());
    try{
      timer.start();
      return invocationContext.proceed();
    } finally {
      timer.stop();
    }
  }
}
