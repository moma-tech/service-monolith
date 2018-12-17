package com.moma.zoffy.wapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.springframework.web.util.HtmlUtils;

/**
 * RequestWrapper
 *
 * <p>Wrapper Request for XSS
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/13/18 - 6:23 PM.
 */
public class RequestWrapper extends HttpServletRequestWrapper {

  public RequestWrapper(HttpServletRequest request) {
    super(request);
  }

  @Override
  public String getHeader(String name) {
    String value = super.getHeader(name);
    return HtmlUtils.htmlEscape(value);
  }

  @Override
  public String getParameter(String name) {
    String value = super.getParameter(name);
    return HtmlUtils.htmlEscape(value);
  }

  @Override
  public String getQueryString() {
    String value = super.getQueryString();
    return HtmlUtils.htmlEscape(value);
  }

  @Override
  public Object getAttribute(String name) {
    Object value = super.getAttribute(name);
    if (value instanceof String) {
      HtmlUtils.htmlEscape((String) value);
    }
    return value;
  }

  @Override
  public String[] getParameterValues(String name) {
    String[] values = super.getParameterValues(name);
    if (values != null) {
      int length = values.length;
      String[] escapeValues = new String[length];
      for (int i = 0; i < length; i++) {
        escapeValues[i] = HtmlUtils.htmlEscape(values[i]);
      }
      return escapeValues;
    }
    return super.getParameterValues(name);
  }
}
