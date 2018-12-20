package com.moma.zoffy.wapper;

import com.moma.zoffy.helper.RequestHelper;
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

  private final byte[] requestBody;

  public RequestWrapper(HttpServletRequest request) {
    super(request);
    requestBody = RequestHelper.getByteBody(request);
  }

  @Override
  public BufferedReader getReader() {
    ServletInputStream inputStream = getInputStream();
    return Objects.isNull(inputStream)
        ? null
        : new BufferedReader(new InputStreamReader(inputStream));
  }

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

  @Override
  public String getHeader(String name) {
    String value = super.getHeader(name);
    return StringUtils.isNotBlank(value) ? HtmlUtils.htmlEscape(value) : "";
  }

  @Override
  public String getParameter(String name) {
    String value = super.getParameter(name);
    return StringUtils.isNotBlank(value) ? HtmlUtils.htmlEscape(value) : "";
  }

  @Override
  public String getQueryString() {
    String value = super.getQueryString();
    return StringUtils.isNotBlank(value) ? HtmlUtils.htmlEscape(value) : "";
  }

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
