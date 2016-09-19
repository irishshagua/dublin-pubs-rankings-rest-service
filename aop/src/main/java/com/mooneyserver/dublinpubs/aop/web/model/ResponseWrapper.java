package com.mooneyserver.dublinpubs.aop.web.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseWrapper<T> {

  private ResponseMetaData metaData;
  private T response;
}
