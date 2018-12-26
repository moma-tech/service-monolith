package com.moma.zoffy.helper.resttemplate;

import com.moma.zoffy.constants.enumeration.JsonNameEnum;
import com.moma.zoffy.helper.JacksonHelper;
import com.moma.zoffy.helper.modelmapper.BeanHelper;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateHelper
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/26/18 - 4:12 PM.
 */
public class RestTemplateHelper {

  /**
   * @author Created by ivan on 5:46 PM 12/26/18.
   *     <p>GET Action
   * @param url :
   * @param responseClass :
   * @param urlVariables :
   * @return T
   */
  public static <T> T get(String url, Class<T> responseClass, Map<String, String> urlVariables) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(new RestErrorHandler());
    if (Objects.isNull(urlVariables) || urlVariables.isEmpty()) {
      return restTemplate.getForObject(url, responseClass);
    }
    return restTemplate.getForObject(url, responseClass, urlVariables);
  }

  /**
   * @author Created by ivan on 5:45 PM 12/26/18.
   *     <p>Post Action
   * @param url :
   * @param responseClass :
   * @param inputHeader :
   * @param requestObject :
   * @param useJsonBody :
   * @return T
   */
  public static <T> T post(
      String url,
      Class<T> responseClass,
      Map<String, Object> inputHeader,
      Object requestObject,
      Boolean useJsonBody,
      JsonNameEnum jsonNameEnum) {

    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(new RestErrorHandler());

    HttpHeaders httpHeaders = formHeader(inputHeader, useJsonBody);

    if (useJsonBody) {
      HttpEntity<String> requestEntity = buildJsonEntity(requestObject, httpHeaders, jsonNameEnum);
      return restTemplate.postForObject(url, requestEntity, responseClass);
    } else {
      HttpEntity<LinkedMultiValueMap<String, Object>> formEntity =
          buildFormEntity(requestObject, httpHeaders);
      return restTemplate.postForObject(url, formEntity, responseClass);
    }
  }

  /**
   * @author Created by ivan on 5:46 PM 12/26/18.
   *     <p>Exchange Action
   * @param url :
   * @param method :
   * @param responseClass :
   * @param inputHeader :
   * @param requestObject :
   * @param urlVariables :
   * @param useJsonBody :
   * @return T
   */
  public static <T> T exchange(
      String url,
      HttpMethod method,
      Class<T> responseClass,
      Map<String, Object> inputHeader,
      Object requestObject,
      Map<String, String> urlVariables,
      Boolean useJsonBody,
      JsonNameEnum jsonNameEnum) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(new RestErrorHandler());

    HttpHeaders httpHeaders = formHeader(inputHeader, useJsonBody);

    if (useJsonBody) {
      HttpEntity<String> requestEntity = buildJsonEntity(requestObject, httpHeaders, jsonNameEnum);
      ResponseEntity<T> resultEntity =
          restTemplate.exchange(url, method, requestEntity, responseClass, urlVariables);
      return resultEntity.getBody();
    } else {
      HttpEntity<LinkedMultiValueMap<String, Object>> formEntity =
          buildFormEntity(requestObject, httpHeaders);
      ResponseEntity<T> resultEntity =
          restTemplate.exchange(url, method, formEntity, responseClass, urlVariables);
      return resultEntity.getBody();
    }
  }

  /**
   * @author Created by ivan on 4:20 PM 12/26/18.
   *     <p>Set Specified Header Contents
   * @param inputHeader :
   * @return org.springframework.http.HttpHeaders
   */
  private static HttpHeaders formHeader(Map<String, Object> inputHeader, Boolean useJsonBody) {
    HttpHeaders httpHeaders = new HttpHeaders();
    if (inputHeader != null) {
      Set<String> keys = inputHeader.keySet();
      for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
        String key = i.next();
        httpHeaders.add(key, inputHeader.get(key).toString());
      }
    }
    if (useJsonBody) {
      httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
    } else {
      httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    }
    httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
    return httpHeaders;
  }

  /**
   * @author Created by ivan on 5:44 PM 12/26/18.
   *     <p>Build Json Content Request Entity
   * @param requestObject :
   * @param httpHeaders :
   * @return org.springframework.http.HttpEntity<java.lang.String>
   */
  private static HttpEntity<String> buildJsonEntity(
      Object requestObject, HttpHeaders httpHeaders, JsonNameEnum jsonNameEnum) {
    String jsonData = "";
    if (Objects.nonNull(requestObject)) {
      if (jsonNameEnum.equals(JsonNameEnum.CAMEL)) {
        jsonData = JacksonHelper.toCamelJson(requestObject);
      } else if (jsonNameEnum.equals(JsonNameEnum.UNDERSCORE)) {
        jsonData = JacksonHelper.toJson(requestObject);
      } else {
        jsonData = JacksonHelper.toJson(requestObject);
      }
    }
    HttpEntity<String> requestJsonEntity = new HttpEntity<>(jsonData, httpHeaders);
    return requestJsonEntity;
  }

  /**
   * @author Created by ivan on 5:45 PM 12/26/18.
   *     <p>Build LinkMap/Form Content Request Entity
   * @param requestObject :
   * @param httpHeaders :
   * @return
   *     org.springframework.http.HttpEntity<org.springframework.util.LinkedMultiValueMap<java.lang.String,java.lang.Object>>
   */
  private static HttpEntity<LinkedMultiValueMap<String, Object>> buildFormEntity(
      Object requestObject, HttpHeaders httpHeaders) {
    LinkedMultiValueMap<String, Object> requestParams = new LinkedMultiValueMap<>();
    if (Objects.nonNull(requestObject)) {
      Map<String, Object> requestMap = BeanHelper.beanToMap(requestObject);
      requestParams.setAll(requestMap);
    }
    HttpEntity<LinkedMultiValueMap<String, Object>> requestFormEntity =
        new HttpEntity<>(requestParams, httpHeaders);
    return requestFormEntity;
  }

  public static void main(String[] args) {}
}
