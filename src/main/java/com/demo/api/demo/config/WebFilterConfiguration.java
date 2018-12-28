package com.demo.api.demo.config;

import com.demo.api.demo.interception.filter.BasicAuthFilter;
import com.demo.framework.interception.filter.XssFilter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

/**
 * WebFilterConfiguration
 *
 * <p>Filter Set Up
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/19/18 - 5:09 PM.
 */
@SpringBootConfiguration
public class WebFilterConfiguration {
  /**
   * @author Created by ivan on 2:30 PM 12/24/18.
   *     <p>Basic Auth Token Filter
   * @return
   *     org.springframework.boot.web.servlet.FilterRegistrationBean<com.moma.service.demo.filter.BasicAuthFilter>
   */
  @Bean
  public FilterRegistrationBean<BasicAuthFilter> basicAuthFilterFilterRegistrationBean() {
    FilterRegistrationBean<BasicAuthFilter> filterFilterRegistrationBean =
        new FilterRegistrationBean<>();
    filterFilterRegistrationBean.setFilter(getBasicAuthFilter());
    filterFilterRegistrationBean.addUrlPatterns("/open/*");
    filterFilterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE - 1);
    return filterFilterRegistrationBean;
  }

  /**
   * @author Created by ivan on 2:31 PM 12/24/18.
   *     <p>Bean with Autowired
   * @return com.demo.api.demo.interception.filter.BasicAuthFilter
   */
  @Bean
  public BasicAuthFilter getBasicAuthFilter() {
    return new BasicAuthFilter();
  }

  /**
   * @author Created by ivan on 2:30 PM 12/24/18.
   *     <p>XSS/Request Wrapper Filter
   * @return
   *     org.springframework.boot.web.servlet.FilterRegistrationBean<com.moma.zoffy.interception.filter.XssFilter>
   */
  @Bean
  public FilterRegistrationBean<XssFilter> xssFilterFilterRegistrationBean() {
    FilterRegistrationBean<XssFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
    filterFilterRegistrationBean.setFilter(new XssFilter());
    filterFilterRegistrationBean.addUrlPatterns("/*");
    filterFilterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE - 2);
    return filterFilterRegistrationBean;
  }
}
