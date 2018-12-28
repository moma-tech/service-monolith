package com.demo.framework.interception.filter;

import com.demo.framework.wapper.RequestWrapper;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * XssFilter
 *
 * <p>Xss/Request Wrapper Filter
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/13/18 - 6:13 PM.
 */
public class XssFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) servletRequest;
    filterChain.doFilter(new RequestWrapper(req), servletResponse);
  }

  @Override
  public void destroy() {}
}
