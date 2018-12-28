package com.demo.framework.helper;

import com.demo.framework.helper.context.ContextHelper;
import com.demo.framework.model.pojo.RequestLogInfo;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 * LogHelper
 *
 * <p>Log Helper
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/13/18 - 4:45 PM.
 */
@Slf4j
public class LogHelper {
  /**
   * @param requestId :
   * @param parameterMap :
   * @param requestBody :
   * @param url :
   * @param mapping :
   * @param method :
   * @param result :
   * @param runTime :
   * @param ip :
   * @param appid :
   * @author Created by ivan on 5:55 PM 12/13/18.
   *     <p>print Request Log Info
   */
  public static void printRequestLogInfo(
      String requestId,
      Map<String, String[]> parameterMap,
      String requestBody,
      String url,
      String mapping,
      String method,
      Object result,
      Long runTime,
      String ip,
      String appid) {

    RequestLogInfo requestLogInfo =
        RequestLogInfo.builder()
            .requestId(requestId)
            .ip(ip)
            .mapping(mapping)
            .method(method)
            .parameterMap(parameterMap)
            .requestBody(Optional.ofNullable(JacksonHelper.parse(requestBody)).orElse(requestBody))
            .result(result)
            .runTime((null != runTime ? System.currentTimeMillis() - runTime : 0) + " ms")
            .url(url)
            .appid(appid)
            .build();

    log.info(JacksonHelper.toJson(requestLogInfo));
  }

  /**
   * @author Created by ivan on 3:58 PM 12/28/18.
   *     <p>Success Response, Log After Returning
   * @param result :
   * @return void
   */
  public static void doAfterReturning(Object result) {
    ResponseHelper.response(ContextHelper.getRequest(), null, result);
  }
}
