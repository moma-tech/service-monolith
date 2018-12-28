package com.demo.api.demo.config;

import com.demo.api.demo.interception.interceptor.DataSignatureInterceptor;
import com.demo.framework.handler.exception.GeneralExceptionHandler;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringMvcConfiguration
 *
 * <p>Spring Mvc Setup
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/18/18 - 6:09 PM.
 */
@SpringBootConfiguration
@Slf4j
public class SpringMvcConfiguration implements WebMvcConfigurer {
  /**
   * @author Created by ivan on 2:26 PM 12/24/18.
   *     <p>Exception Handler
   * @param exceptionResolvers :
   */
  @Override
  public void configureHandlerExceptionResolvers(
      List<HandlerExceptionResolver> exceptionResolvers) {
    log.info("Configure Exception");
    exceptionResolvers.add(new GeneralExceptionHandler());
  }

  /**
   * @author Created by ivan on 6:22 PM 12/24/18.
   *     <p>Interceptors
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    log.info("Configure Interceptor");
    registry
        .addInterceptor(getDataSignatureInterceptor())
        .addPathPatterns("/open/**")
        .excludePathPatterns("/open/token/request");
  }

  /**
   * @author Created by ivan on 6:28 PM 12/24/18.
   *     <p>//For Autowire
   * @return com.demo.api.demo.interception.interceptor.DataSignatureInterceptor
   */
  @Bean
  DataSignatureInterceptor getDataSignatureInterceptor() {
    return new DataSignatureInterceptor();
  }
  /*
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    log.info("Configure Converter");
    converters.forEach(HttpMessageConverterWrapper.objectMapperWrapper());
  }*/
}
