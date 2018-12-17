package com.moma.zoffy.constants.enumeration;

import com.moma.zoffy.response.dto.ApiStatusInfo;

/**
 * ApiStatusCodeEnum
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/15/18 - 3:12 PM.
 */
public enum ApiStatusCodeEnum {
  /** -1 */
  UNKNOWN(-1, "未知错误"),
  /** 10000 */
  UNKOWN_API_TYPE(10000, "未知的接口类型"),
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
