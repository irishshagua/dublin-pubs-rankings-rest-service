package com.mooneyserver.dublinpubs.aop.web;

import com.mooneyserver.dublinpubs.aop.web.model.NullResponse;
import com.mooneyserver.dublinpubs.aop.web.model.ResponseMetaData;
import com.mooneyserver.dublinpubs.aop.web.model.ResponseWrapper;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Collection;

/**
 * Class to be used in an AOP style to format the response of rest calls for JAX-RS resources.
 * This class is to be used as an Interceptor and should not be manually created.
 */
@WebResponse
@Interceptor
public class WebResponseInterceptor {

  private Logger logger;

  @Inject
  public WebResponseInterceptor(Logger logger) {
    this.logger = logger;
  }

  /**
   * Add a hook to change the response of any REST calls so that the response is wrapped in
   * an object which can provide some extra useful information to the HTTP client.
   */
  @AroundInvoke
  public Object bundleResponseInWrapper(InvocationContext invocationContext) throws Exception {
    Object initialResponse = null;
    ResponseMetaData metaData = null;
    try {
      initialResponse = invocationContext.proceed();
      if (initialResponse == null) {
        metaData = ResponseMetaData.builder().responseType(NullResponse.class)
            .itemCount(0).build();
      } else {
        metaData = ResponseMetaData.builder().responseType(initialResponse.getClass())
            .itemCount(sizeOfResponse(initialResponse)).build();
      }
    } catch (Exception e) {
      logger.error("Exception thrown while processing {}:{}. {}", invocationContext.getTarget(),
          invocationContext.getMethod(), e.fillInStackTrace());
      metaData = ResponseMetaData.builder().responseType(e.getClass()).errorMessage(e.getMessage()).build();
      initialResponse = e;
    }

    return ResponseWrapper.builder().metaData(metaData).response(initialResponse).build();
  }

  int sizeOfResponse(Object response) {
    if (response instanceof Collection) {
      return ((Collection) response).size();
    } else {
      return 1;
    }
  }
}
