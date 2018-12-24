package com.moma.zoffy.response;

import com.moma.zoffy.constants.enumeration.HttpStatusCodeEnum;
import com.moma.zoffy.helper.ResponseHelper;
import com.moma.zoffy.response.dto.ApiStatusInfo;
import com.moma.zoffy.response.dto.SuccessResponse;

/**
 * Response
 *
 * <p>Response Object
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/14/18 - 5:10 PM.
 */
public class Response<T> implements java.io.Serializable {

  private static final long serialVersionUID = -2718986249962869007L;

  /**
   * @author Created by ivan on 3:35 PM 12/24/18.
   *     <p>//success Response
   * @param result :
   * @return com.moma.zoffy.response.Response<T>
   */
  public static <T> Response<T> success(T result) {
    return SuccessResponse.<T>builder()
        .code(HttpStatusCodeEnum.OK.code())
        .msg(HttpStatusCodeEnum.OK.msg())
        .result(result)
        .build();
  }

  /**
   * @author Created by ivan on 3:35 PM 12/24/18.
   *     <p>//fail Response
   * @param exception :
   * @param httpStatusInfo :
   * @return com.moma.zoffy.response.Response
   */
  public static Response fail(Exception exception, ApiStatusInfo httpStatusInfo) {
    return ResponseHelper.buildFailedResponse(null, exception, httpStatusInfo);
  }
}
