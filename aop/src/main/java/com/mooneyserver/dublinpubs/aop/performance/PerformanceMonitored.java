package com.mooneyserver.dublinpubs.aop.performance;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@InterceptorBinding
@Retention(RUNTIME)
@Target({PACKAGE, METHOD, TYPE})
public @interface PerformanceMonitored {}
