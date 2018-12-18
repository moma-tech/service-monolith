package com.moma.service.demo.model.param;

import com.moma.zoffy.helper.JacksonHelper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * BaseParam
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/18/18 - 2:46 PM.
 */
@Data
public class BaseParam<T> {
  @ApiModelProperty(value = "权限Token", required = false)
  private String accessToken;

  @ApiModelProperty(value = "签名", required = false)
  private String sign;

  @ApiModelProperty(value = "时间戳", required = false)
  private Long timestamp;

  @ApiModelProperty(value = "请求人ID", required = false)
  private String employeeId;

  @ApiModelProperty(value = "请求人类型", required = false)
  private String employeeType;

  @ApiModelProperty(value = "请求ID", required = false)
  private String requestId;

  @ApiModelProperty(value = "数据", required = false)
  private T data;

  public String toJsonData() {
    return JacksonHelper.toJson(this.getData());
  }
}
