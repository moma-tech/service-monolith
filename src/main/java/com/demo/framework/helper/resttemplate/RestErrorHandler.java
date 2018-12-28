package com.demo.framework.helper.resttemplate;

import java.io.IOException;
import java.net.URI;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * RestErrorHandler
 *
 * <p>RestTemplate Error Handler
 *
 * <p>!!Ignore as All should catch by framework
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/26/18 - 4:10 PM.
 */
public class RestErrorHandler implements ResponseErrorHandler {

  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return false;
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {}

  @Override
  public void handleError(URI url, HttpMethod method, ClientHttpResponse response)
      throws IOException {}
}
