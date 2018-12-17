package com.moma.service.demo.constants.enumeration;

import com.moma.zoffy.constants.enumeration.ApiStatusCodeEnum;
import com.moma.zoffy.handler.exception.exceptions.ServiceException;

/**
 * ApiTypeEnum
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/15/18 - 5:20 PM.
 */
public enum ApiTypeEnum {

  /** 需要登录 */
  TOKEN(0),
  /** 开放,无需鉴权 */
  OPEN(2),
  /** 需要鉴定是否包含权限 */
  AUTH(3);

  private int type;

  ApiTypeEnum(int type) {
    this.type = type;
  }

  public static ApiTypeEnum getApiTypeEnum(int type) {
    for (ApiTypeEnum apiTypeEnum : ApiTypeEnum.values()) {
      if (apiTypeEnum.getType() == type) {
        return apiTypeEnum;
      }
    }
    throw new ServiceException(ApiStatusCodeEnum.UNKOWN_API_TYPE);
  }

  public int getType() {
    return type;
  }
}
