package com.demo.framework.wapper;

import com.demo.framework.helper.RequestHelper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
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

  /** Copy Body Byte[] */
  private final byte[] requestBody;
  /**
   * @author Created by ivan on 3:37 PM 12/24/18.
   *     <p>//Wrapper
   * @param request :
   * @return
   */
  public RequestWrapper(HttpServletRequest request) {
    super(request);
    requestBody = RequestHelper.getByteBody(request);
  }
  /**
   * @author Created by ivan on 3:38 PM 12/24/18.
   *     <p>//Get Request Reader
   * @return java.io.BufferedReader
   */
  @Override
  public BufferedReader getReader() {
    ServletInputStream inputStream = getInputStream();
    return Objects.isNull(inputStream)
        ? null
        : new BufferedReader(new InputStreamReader(inputStream));
  }
  /**
   * @author Created by ivan on 3:39 PM 12/24/18.
   *     <p>//Get Request Input Stream
   * @return javax.servlet.ServletInputStream
   */
  @Override
  public ServletInputStream getInputStream() {
    if (ObjectUtils.isEmpty(requestBody)) {
      return null;
    }
    final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
    return new ServletInputStream() {

      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      @SuppressWarnings("EmptyMethod")
      public void setReadListener(ReadListener readListener) {}

      @Override
      public int read() {
        return bais.read();
      }
    };
  }

  /**
   * @author Created by ivan on 3:39 PM 12/24/18.
   *     <p>//Get Request Header,escape
   * @param name :
   * @return java.lang.String
   */
  @Override
  public String getHeader(String name) {
    String value = super.getHeader(name);
    return StringUtils.isNotBlank(value) ? HtmlUtils.htmlEscape(value) : "";
  }
  /**
   * @author Created by ivan on 3:40 PM 12/24/18.
   *     <p>//Get Request Parameter,escape
   * @param name :
   * @return java.lang.String
   */
  @Override
  public String getParameter(String name) {
    String value = super.getParameter(name);
    return StringUtils.isNotBlank(value) ? HtmlUtils.htmlEscape(value) : "";
  }
  /**
   * @author Created by ivan on 3:40 PM 12/24/18.
   *     <p>//get Request Query String, escape
   * @return java.lang.String
   */
  @Override
  public String getQueryString() {
    String value = super.getQueryString();
    return StringUtils.isNotBlank(value) ? HtmlUtils.htmlEscape(value) : "";
  }
  /**
   * @author Created by ivan on 3:41 PM 12/24/18.
   *     <p>//Get Request Attribute, escape
   * @param name :
   * @return java.lang.Object
   */
  @Override
  public Object getAttribute(String name) {
    Object value = super.getAttribute(name);
    if (value instanceof String) {
      if (StringUtils.isNotBlank(value.toString())) {
        HtmlUtils.htmlEscape((String) value);
      }
    }
    return value;
  }
  /**
   * @author Created by ivan on 3:41 PM 12/24/18.
   *     <p>//Get Request Parameter Vlaue, escape
   * @param name :
   * @return java.lang.String[]
   */
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
