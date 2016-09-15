package com.mooneyserver.dublinpubs.aop.performance;

import lombok.Builder;
import lombok.Data;

import javax.interceptor.InvocationContext;
import java.time.Duration;

@Builder
@Data
public class PerformanceDetail {

  private InvocationContext invocationContext;
  private Duration duration;
}
