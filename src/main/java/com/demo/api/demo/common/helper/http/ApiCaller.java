package com.demo.api.demo.common.helper.http;

import com.demo.api.demo.common.model.base.BaseResponse;
import com.demo.api.demo.common.model.base.BaseResponseList;
import com.demo.framework.constants.ApiConstants;
import com.demo.framework.constants.enumeration.JsonNameEnum;
import com.demo.framework.helper.JacksonHelper;
import com.demo.framework.helper.resttemplate.RestTemplateHelper;
import java.util.Map;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * ApiCaller
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/26/18 - 5:47 PM.
 */
@Slf4j
public class ApiCaller<T, R> {
  /**
   * @author Created by ivan on 3:01 PM 12/28/18.
   *     <p>//Do Get with Camel Parameters
   * @param url :
   * @param request :
   * @param response :
   * @param inputHeader :
   * @return com.demo.api.demo.common.model.base.BaseResponse<R>
   */
  public BaseResponse<R> doGet(
      @NonNull String url, T request, R response, Map<String, String> inputHeader) {
    return doGet(url, request, response, inputHeader, JsonNameEnum.CAMEL);
  }

  /**
   * @author Created by ivan on 3:01 PM 12/28/18.
   *     <p>do Get with define Parameters format
   * @param url :
   * @param request :
   * @param response :
   * @param inputHeader :
   * @param jsonNameEnum :
   * @return com.demo.api.demo.common.model.base.BaseResponse<R>
   */
  @SuppressWarnings(value = "unchecked")
  public BaseResponse<R> doGet(
      @NonNull String url,
      T request,
      R response,
      Map<String, String> inputHeader,
      JsonNameEnum jsonNameEnum) {
    log.info("Get Request: " + url + " | Path Params:" + JacksonHelper.toCamelJson(request));
    String resultString = RestTemplateHelper.get(url, inputHeader, request, jsonNameEnum);
    log.info("Get Request: " + url + " | Response String: " + resultString);
    BaseResponse<R> result =
        (BaseResponse<R>) (BaseResponse.fromJson(resultString, response.getClass()));
    if (ApiConstants.SUCCESS.compareTo(result.getCode()) == 0) {
      result.setRequestId(MDC.get(ApiConstants.REQUEST_ID));
    }
    return result;
  }

  /**
   * @author Created by ivan on 3:01 PM 12/28/18.
   *     <p>do List Get with Camel Parameters
   * @param url :
   * @param request :
   * @param response :
   * @param inputHeader :
   * @return com.demo.api.demo.common.model.base.BaseResponseList<R>
   */
  public BaseResponseList<R> doQueryList(
      @NonNull String url, T request, R response, Map<String, String> inputHeader) {
    return doQueryList(url, request, response, inputHeader, JsonNameEnum.CAMEL);
  }

  /**
   * @author Created by ivan on 3:02 PM 12/28/18.
   *     <p>Do List Get with define Parameters format
   * @param url :
   * @param request :
   * @param response :
   * @param inputHeader :
   * @param jsonNameEnum :
   * @return com.demo.api.demo.common.model.base.BaseResponseList<R>
   */
  @SuppressWarnings(value = "unchecked")
  public BaseResponseList<R> doQueryList(
      @NonNull String url,
      T request,
      R response,
      Map<String, String> inputHeader,
      JsonNameEnum jsonNameEnum) {
    log.info("Get List Request: " + url + " | Path Params: " + JacksonHelper.toCamelJson(request));
    String resultString = RestTemplateHelper.get(url, inputHeader, request, jsonNameEnum);
    log.info("Get List Request: " + url + " | Response String: " + resultString);
    BaseResponseList<R> resultList = BaseResponseList.fromJson(resultString, response.getClass());
    if (ApiConstants.SUCCESS.compareTo(resultList.getCode()) == 0) {
      resultList.setRequestId(MDC.get(ApiConstants.REQUEST_ID));
    }
    return resultList;
  }

  /**
   * @author Created by ivan on 4:17 PM 12/27/18.
   *     <p>do Post with Camel Json Body
   * @param url :
   * @param request :
   * @param response :
   * @param inputHeader :
   * @return com.demo.api.demo.common.model.base.BaseResponse<R>
   */
  public BaseResponse<R> doPost(
      @NonNull String url, T request, R response, Map<String, String> inputHeader) {
    return doPost(url, request, response, inputHeader, JsonNameEnum.CAMEL);
  }

  /**
   * @author Created by ivan on 4:18 PM 12/27/18.
   *     <p>do Post with custom Json Body
   * @param url :
   * @param request :
   * @param response :
   * @param inputHeader :
   * @param jsonNameEnum :
   * @return com.demo.api.demo.common.model.base.BaseResponse<R>
   */
  @SuppressWarnings(value = "unchecked")
  public BaseResponse<R> doPost(
      @NonNull String url,
      T request,
      R response,
      Map<String, String> inputHeader,
      JsonNameEnum jsonNameEnum) {
    log.info(
        "Get List Request: " + url + " | Object Params: " + JacksonHelper.toCamelJson(request));

    String resultString =
        RestTemplateHelper.post(url, String.class, inputHeader, request, true, jsonNameEnum);
    log.info("Post Request: " + url + " | Response String: " + resultString);
    return (BaseResponse<R>) (BaseResponse.fromJson(resultString, response.getClass()));
  }

  /**
   * @author Created by ivan on 4:19 PM 12/27/18.
   *     <p>do Post with Camel Form Body
   * @param url :
   * @param request :
   * @param response :
   * @param inputHeader :
   * @return com.demo.api.demo.common.model.base.BaseResponse<R>
   */
  public BaseResponse<R> doFormPost(
      @NonNull String url, T request, R response, Map<String, String> inputHeader) {
    return doFormPost(url, request, response, inputHeader, JsonNameEnum.CAMEL);
  }

  /**
   * @author Created by ivan on 4:19 PM 12/27/18.
   *     <p>do Post with custom format Form Body
   * @param url :
   * @param request :
   * @param response :
   * @param inputHeader :
   * @param jsonNameEnum :
   * @return com.demo.api.demo.common.model.base.BaseResponse<R>
   */
  @SuppressWarnings(value = "unchecked")
  public BaseResponse<R> doFormPost(
      @NonNull String url,
      T request,
      R response,
      Map<String, String> inputHeader,
      JsonNameEnum jsonNameEnum) {
    log.info(
        "Get List Request: " + url + " | Object Params: " + JacksonHelper.toCamelJson(request));

    String resultString =
        RestTemplateHelper.post(url, String.class, inputHeader, request, false, jsonNameEnum);
    log.info("Post Request: " + url + " | Response String: " + resultString);
    return (BaseResponse<R>) (BaseResponse.fromJson(resultString, response.getClass()));
  }
}
