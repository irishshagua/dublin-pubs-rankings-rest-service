package com.mooneyserver.dublinpubs.aop.logging

import spock.lang.Specification

import javax.enterprise.inject.spi.InjectionPoint
import java.lang.reflect.Member

class LoggingProducerTest extends Specification {

  def "Creates a Logger with the class name from the injection point"() {
    given:
      def logProducer = new LoggerProducer()
      def injectionPoint = Mock(InjectionPoint)
      def member = Mock(Member)

      injectionPoint.getMember() >> member
      member.getDeclaringClass() >> LoggingProducerTest.class

    when:
      def logger = logProducer.logger(injectionPoint)

    then:
      logger.name == LoggingProducerTest.class.getName()
  }
}
