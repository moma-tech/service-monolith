package com.demo.framework.handler.exception.exceptions;

import com.demo.framework.constants.enumeration.HttpStatusCodeEnum;
import com.demo.framework.response.dto.ApiStatusInfo;

/**
 * ApiException
 *
 * <p>Exception throws from api
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/15/18 - 2:55 PM.
 */
public class ApiException extends RuntimeException {

  private static final long serialVersionUID = -2344691769119882244L;
  private final ApiStatusInfo apiStatusInfo;

  public ApiException(HttpStatusCodeEnum errorCodeEnum) {
    super(errorCodeEnum.msg());
    this.apiStatusInfo = errorCodeEnum.transform();
  }

  public ApiException(ApiStatusInfo apiStatusInfo) {
    super(apiStatusInfo.getMsg());
    this.apiStatusInfo = apiStatusInfo;
  }

  public ApiStatusInfo getApiStatusInfo() {
    return apiStatusInfo;
  }
}
