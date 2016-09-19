package com.mooneyserver.dublinpubs.aop.web.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseMetaData {

  private Integer itemCount;
  private Class responseType;
  private String errorMessage;
}
