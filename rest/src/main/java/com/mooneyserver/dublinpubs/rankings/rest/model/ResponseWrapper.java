package com.mooneyserver.dublinpubs.rankings.rest.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseWrapper<T> {

    private ResponseMetaData metaData;
    private T response;
}
