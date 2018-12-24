package com.moma.service.demo.model.param;

import com.moma.zoffy.helper.JacksonHelper;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * BaseParam
 *
 * <p>Basic Parameters for Request
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/18/18 - 2:46 PM.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseParam<T> {
  @ApiModelProperty(value = "权限Token", required = true)
  @NotBlank(message = "access_token 参数不能为空")
  private String accessToken;

  @ApiModelProperty(value = "签名", required = true)
  @NotBlank(message = "sign 参数不能为空")
  private String sign;

  @ApiModelProperty(value = "时间戳", required = true)
  @NotBlank(message = "timestamp 参数不能为空")
  private Long timestamp;

  @ApiModelProperty(value = "请求人ID", required = true)
  @NotBlank(message = "employee_id 参数不能为空")
  private String employeeId;

  @ApiModelProperty(value = "请求人类型", required = true)
  @NotBlank(message = "employee_type 参数不能为空")
  private String employeeType;

  @ApiModelProperty(value = "请求ID", required = true)
  private String requestId;

  @ApiModelProperty(value = "数据", required = true)
  @NotBlank(message = "data 参数不能为空")
  private T data;

  public String getJsonData() {
    return JacksonHelper.toJson(this.getData());
  }
}
