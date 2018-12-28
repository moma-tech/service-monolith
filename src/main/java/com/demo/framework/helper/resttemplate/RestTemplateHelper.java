package com.demo.framework.helper.resttemplate;

import com.demo.framework.constants.enumeration.JsonNameEnum;
import com.demo.framework.helper.JacksonHelper;
import com.demo.framework.helper.modelmapper.BeanHelper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * RestTemplateHelper
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/26/18 - 4:12 PM.
 */
@Slf4j
public class RestTemplateHelper {

  /**
   * @author Created by ivan on 3:38 PM 12/27/18.
   *     <p>Get Action
   * @param url :
   * @param inputHeader :
   * @param requestObject :
   * @param jsonNameEnum :
   * @return java.lang.String
   */
  public static String get(
      String url,
      Map<String, String> inputHeader,
      Object requestObject,
      JsonNameEnum jsonNameEnum) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(new RestErrorHandler());
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
    url = expandUrl(url, requestObject, jsonNameEnum);
    log.info("Get Request URL=" + url);
    return exchange(
        url,
        HttpMethod.GET,
        String.class,
        httpHeaders,
        null,
        new HashMap<String, String>(0),
        true,
        jsonNameEnum);
  }
  /**
   * @author Created by ivan on 3:38 PM 12/27/18.
   *     <p>URL Build for Get Action
   * @param url :
   * @param requestObject :
   * @param jsonNameEnum :
   * @return java.lang.String
   */
  @SuppressWarnings(value = "unchecked")
  private static String expandUrl(String url, Object requestObject, JsonNameEnum jsonNameEnum) {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    if (jsonNameEnum.equals(JsonNameEnum.CAMEL)) {
      Map<String, String> urlParameters = BeanHelper.beanToStringMap(requestObject);
      params.setAll(urlParameters);
    } else {
      Map<String, String> urlParameters =
          JacksonHelper.readValue(JacksonHelper.toJson(requestObject), Map.class);
      params.setAll(urlParameters);
    }
    url = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build().toUriString();
    return url;
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
      Map<String, String> inputHeader,
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
      HttpEntity<LinkedMultiValueMap<String, String>> formEntity =
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
   * @param httpHeaders :
   * @param requestObject :
   * @param urlVariables :
   * @param useJsonBody :
   * @return T
   */
  public static <T> T exchange(
      String url,
      HttpMethod method,
      Class<T> responseClass,
      HttpHeaders httpHeaders,
      Object requestObject,
      Map<String, String> urlVariables,
      Boolean useJsonBody,
      JsonNameEnum jsonNameEnum) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(new RestErrorHandler());

    if (useJsonBody) {
      HttpEntity<String> requestEntity = buildJsonEntity(requestObject, httpHeaders, jsonNameEnum);
      ResponseEntity<T> resultEntity =
          restTemplate.exchange(url, method, requestEntity, responseClass, urlVariables);
      return resultEntity.getBody();
    } else {
      HttpEntity<LinkedMultiValueMap<String, String>> formEntity =
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
  private static HttpHeaders formHeader(Map<String, String> inputHeader, Boolean useJsonBody) {
    HttpHeaders httpHeaders = new HttpHeaders();
    if (inputHeader != null) {
      Set<String> keys = inputHeader.keySet();
      for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
        String key = i.next();
        httpHeaders.add(key, inputHeader.get(key));
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
  private static HttpEntity<LinkedMultiValueMap<String, String>> buildFormEntity(
      Object requestObject, HttpHeaders httpHeaders) {
    LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
    if (Objects.nonNull(requestObject)) {
      Map<String, String> requestMap = BeanHelper.beanToStringMap(requestObject);
      requestParams.setAll(requestMap);
    }
    HttpEntity<LinkedMultiValueMap<String, String>> requestFormEntity =
        new HttpEntity<>(requestParams, httpHeaders);
    return requestFormEntity;
  }

  public static void main(String[] args) {}
}
