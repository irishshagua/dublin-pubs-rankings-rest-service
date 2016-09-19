package com.mooneyserver.dublinpubs.metrics

import com.codahale.metrics.Timer
import spock.lang.Specification

class CodahaleTaskTimerTest extends Specification {

  def "Starting the timer will start the internal timer"() {
    given:
      def timer = Mock(Timer)
      def timerContext = Mock(Timer.Context)
      def taskTimer = new CodahaleTaskTimer(timer)

      timer.time >> timerContext

    when:
      taskTimer.start()

    then:
      1 * timer.time()
  }

  def "Repeated calls to start the task timer will not be propogated to the internal timer"() {
    given:
      def timer = Mock(Timer)
      def timerContext = Mock(Timer.Context)
      def taskTimer = new CodahaleTaskTimer(timer)

    when:
      taskTimer.start()
      taskTimer.start()
      taskTimer.start()
      taskTimer.start()
      taskTimer.start()
      taskTimer.start()
      taskTimer.start()

    then:
      1 * timer.time() >> timerContext
  }

  def "Stop calls will only work if start has been called"() {
    given:
      def timer = Mock(Timer)
      def timerContext = Mock(Timer.Context)
      def taskTimer = new CodahaleTaskTimer(timer)

      timer.time() >> timerContext

    when:
      taskTimer.stop()

    then:
      0 * timerContext.stop()
  }

  def "Stopping the task timer will be propogated through to the internal timer"() {
    given:
      def timer = Mock(Timer)
      def timerContext = Mock(Timer.Context)
      def taskTimer = new CodahaleTaskTimer(timer)

      timer.time() >> timerContext

    when:
      taskTimer.start()
      taskTimer.stop()

    then:
      1 * timerContext.stop()
  }
}
