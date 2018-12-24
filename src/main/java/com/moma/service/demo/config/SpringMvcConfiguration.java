package com.moma.service.demo.config;

import com.moma.service.demo.filter.interceptor.DataSignatureInterceptor;
import com.moma.zoffy.handler.exception.GeneralExceptionHandler;
import java.util.List;
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
public class SpringMvcConfiguration implements WebMvcConfigurer {
  /**
   * @author Created by ivan on 2:26 PM 12/24/18.
   *     <p>Exception Handler
   * @param exceptionResolvers :
   * @return void
   */
  @Override
  public void configureHandlerExceptionResolvers(
      List<HandlerExceptionResolver> exceptionResolvers) {
    exceptionResolvers.add(new GeneralExceptionHandler());
  }
  /**
   * @author Created by ivan on 6:22 PM 12/24/18.
   *     <p>Interceptors
   * @return void
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(getDataSignatureInterceptor())
        .addPathPatterns("/open/*")
        .excludePathPatterns("/open/token/request");
  }

  /**
   * @author Created by ivan on 6:28 PM 12/24/18.
   *     <p>//For Autowire
   * @return com.moma.service.demo.filter.interceptor.DataSignatureInterceptor
   */
  @Bean
  DataSignatureInterceptor getDataSignatureInterceptor() {
    return new DataSignatureInterceptor();
  }
}
