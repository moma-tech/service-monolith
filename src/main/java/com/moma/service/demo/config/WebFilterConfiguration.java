package com.moma.service.demo.config;

import com.moma.service.demo.filter.BasicAuthFilter;
import com.moma.zoffy.interception.filter.XssFilter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

/**
 * WebFilterConfiguration
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/19/18 - 5:09 PM.
 */
@SpringBootConfiguration
public class WebFilterConfiguration {
  @Bean
  public FilterRegistrationBean<BasicAuthFilter> basicAuthFilterFilterRegistrationBean() {
    FilterRegistrationBean<BasicAuthFilter> filterFilterRegistrationBean =
        new FilterRegistrationBean<>();
    filterFilterRegistrationBean.setFilter(getBasicAuthFilter());
    filterFilterRegistrationBean.addUrlPatterns("/open/*");
    filterFilterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE - 1);
    return filterFilterRegistrationBean;
  }

  @Bean
  public BasicAuthFilter getBasicAuthFilter() {
    return new BasicAuthFilter();
  }

  public FilterRegistrationBean<XssFilter> xssFilterFilterRegistrationBean() {
    FilterRegistrationBean<XssFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
    filterFilterRegistrationBean.setFilter(new XssFilter());
    filterFilterRegistrationBean.addUrlPatterns("/*");
    filterFilterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE - 2);
    return filterFilterRegistrationBean;
  }
}
