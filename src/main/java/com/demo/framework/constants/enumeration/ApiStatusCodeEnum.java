package com.demo.framework.constants.enumeration;

import com.demo.framework.response.dto.ApiStatusInfo;

/**
 * ApiStatusCodeEnum
 *
 * <p>Self Defined Api Status Code
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/15/18 - 3:12 PM.
 */
public enum ApiStatusCodeEnum {
  /** -1 */
  UNKNOWN(-1, "未知错误"),
  /** 10000 */
  UNKOWN_API_TYPE(10000, "未知的接口类型"),
  /** 11000 */
  UN_SIGN(11000, "sign签名未传递"),
  /** 11001 */
  SIGN_ERROR(11001, "sign签名不正确"),
  /** 11003 */
  UN_TIMESTAMP(11003, "timestamp时间戳未传递"),
  /** 11004 */
  UN_DATA(11004, "data数据未传递"),
  /** 11009 */
  UN_VALID_TIMESTAMP(11009, "timestamp失效"),
  ;
  /** Code */
  private int code;
  /** Message */
  private String msg;

  ApiStatusCodeEnum(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public int code() {
    return this.code;
  }

  public String msg() {
    return this.msg;
  }

  public ApiStatusInfo transform() {
    return ApiStatusInfo.builder().code(code()).msg(msg()).build();
  }
}
