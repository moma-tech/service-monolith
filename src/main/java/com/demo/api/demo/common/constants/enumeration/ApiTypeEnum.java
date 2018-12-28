package com.demo.api.demo.common.constants.enumeration;

import com.demo.framework.constants.enumeration.ApiStatusCodeEnum;
import com.demo.framework.handler.exception.exceptions.ServiceException;

/**
 * ApiTypeEnum
 *
 * <p>Api Type
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

  /**
   * @author Created by ivan on 2:52 PM 12/28/18.
   *     <p>Get Type Enum by type code
   * @param type :
   * @return com.demo.api.demo.common.constants.enumeration.ApiTypeEnum
   */
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

  @Override
  public String toString() {
    return type + "";
  }
}
