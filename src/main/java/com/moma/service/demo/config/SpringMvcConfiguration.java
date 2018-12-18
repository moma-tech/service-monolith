package com.moma.service.demo.config;

import com.moma.zoffy.handler.exception.GeneralExceptionHandler;
import java.util.List;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringMvcConfiguration
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/18/18 - 6:09 PM.
 */
@SpringBootConfiguration
public class SpringMvcConfiguration implements WebMvcConfigurer {

  @Override
  public void configureHandlerExceptionResolvers(
      List<HandlerExceptionResolver> exceptionResolvers) {
    exceptionResolvers.add(new GeneralExceptionHandler());
  }
}
