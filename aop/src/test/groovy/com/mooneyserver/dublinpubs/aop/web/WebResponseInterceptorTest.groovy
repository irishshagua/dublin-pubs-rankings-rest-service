package com.mooneyserver.dublinpubs.aop.web

import com.mooneyserver.dublinpubs.aop.web.model.NullResponse
import com.mooneyserver.dublinpubs.aop.web.model.ResponseMetaData
import com.mooneyserver.dublinpubs.aop.web.model.ResponseWrapper
import com.sun.xml.internal.ws.handler.HandlerException
import org.slf4j.Logger
import spock.lang.Specification

import javax.interceptor.InvocationContext

class WebResponseInterceptorTest extends Specification {

  def "A null response should be wrapped correctly in a web response"() {
    given:
      def logger = Mock(Logger)
      def invocationCtxt = Mock(InvocationContext)
      def interceptor = new WebResponseInterceptor(logger)

    invocationCtxt.proceed() >> null


    when:
      def responseWrapper = interceptor.bundleResponseInWrapper(invocationCtxt)

    then:
      responseWrapper == ResponseWrapper.builder()
          .metaData(ResponseMetaData.builder().itemCount(0).responseType(NullResponse.class).build())
          .response(null).build()
  }

  def "A valid response should be wrapped correctly in a web response"() {
    given:
      def logger = Mock(Logger)
      def invocationCtxt = Mock(InvocationContext)
      def interceptor = new WebResponseInterceptor(logger)

      invocationCtxt.proceed() >> "Some Response"

    when:
      def responseWrapper = interceptor.bundleResponseInWrapper(invocationCtxt)

    then:
      responseWrapper == ResponseWrapper.builder()
          .metaData(ResponseMetaData.builder().itemCount(1).responseType(String.class).build())
          .response("Some Response").build()
  }

  def "A valid response of type collection should be wrapped correctly in a web response"() {
    given:
      def logger = Mock(Logger)
      def invocationCtxt = Mock(InvocationContext)
      def interceptor = new WebResponseInterceptor(logger)

      invocationCtxt.proceed() >> ["one", "two", "three"]

    when:
      def responseWrapper = interceptor.bundleResponseInWrapper(invocationCtxt)

    then:
      responseWrapper == ResponseWrapper.builder()
          .metaData(ResponseMetaData.builder().itemCount(3).responseType(ArrayList.class).build())
          .response(Arrays.asList("one", "two", "three")).build()
  }

  def "An exception thrown from the method invocation should be wrapped correctly in a web response"() {
    given:
      def logger = Mock(Logger)
      def invocationCtxt = Mock(InvocationContext)
      def interceptor = new WebResponseInterceptor(logger)
      def exception = new Exception("Something went wrong")

      invocationCtxt.proceed() >> {throw exception}

    when:
      def responseWrapper = interceptor.bundleResponseInWrapper(invocationCtxt)

    then:
      responseWrapper == ResponseWrapper.builder()
          .metaData(ResponseMetaData.builder()
            .responseType(Exception.class)
            .errorMessage("Something went wrong").build())
          .response(exception).build()
  }
}
