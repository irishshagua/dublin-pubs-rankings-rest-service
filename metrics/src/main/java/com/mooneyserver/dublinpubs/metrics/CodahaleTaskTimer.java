package com.mooneyserver.dublinpubs.metrics;

import com.codahale.metrics.Timer;

class CodahaleTaskTimer implements TaskTimer {

  private Timer timer;
  private Timer.Context timerContext;

  public CodahaleTaskTimer(Timer timer) {
    this.timer = timer;
  }

  @Override
  public void start() {
    if (timerContext == null)
      timerContext = timer.time();
  }

  @Override
  public void stop() {
    if (timerContext != null)
      timerContext.stop();
  }
}
