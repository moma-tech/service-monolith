package com.moma.zoffy.handler.exception.exceptions;

import com.moma.zoffy.constants.enumeration.ApiStatusCodeEnum;
import com.moma.zoffy.response.dto.ApiStatusInfo;

/**
 * ApiExcpetion
 *
 * <p>TODO
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
