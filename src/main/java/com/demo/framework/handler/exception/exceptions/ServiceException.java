package com.demo.framework.handler.exception.exceptions;

import com.demo.framework.constants.enumeration.ApiStatusCodeEnum;
import com.demo.framework.response.dto.ApiStatusInfo;

/**
 * ServiceException
 *
 * <p>Exception throws from Service
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/15/18 - 2:41 PM.
 */
public class ServiceException extends RuntimeException {

  private static final long serialVersionUID = -5813537479431079738L;
  private final ApiStatusInfo apiStatusInfo;

  public ServiceException(ApiStatusCodeEnum errorCodeEnum) {
    super(errorCodeEnum.msg());
    this.apiStatusInfo = errorCodeEnum.transform();
  }

  public ServiceException(ApiStatusInfo apiStatusInfo) {
    super(apiStatusInfo.getMsg());
    this.apiStatusInfo = apiStatusInfo;
  }

  public ApiStatusInfo getApiStatusInfo() {
    return apiStatusInfo;
  }
}
