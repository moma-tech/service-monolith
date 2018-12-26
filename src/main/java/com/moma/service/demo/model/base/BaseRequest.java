package com.moma.service.demo.model.base;

import com.moma.zoffy.helper.JacksonHelper;
import com.moma.zoffy.model.domain.SuperModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * BaseParam
 *
 * <p>Basic Parameters for Request
 *
 * <p>
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/18/18 - 2:46 PM.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class BaseRequest<T> extends SuperModel {
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

  public String presentJsonData() {
    return JacksonHelper.toJson(this.getData());
  }

  public static void main(String[] args) {
    BaseRequest baseRequest = new BaseRequest();
    baseRequest.setAccessToken("123123123123");
    baseRequest.setRequestId("aaaaaaa");
    BaseResponse<BaseRequest> r1 = new BaseResponse<>();
    r1.setData(baseRequest);
    String cs = JacksonHelper.toCamelJson(r1);
    String us = JacksonHelper.toJson(r1);

    System.out.println(JacksonHelper.toCamelJson(baseRequest));
    System.out.println(JacksonHelper.toJson(baseRequest));

    BaseResponse<BaseRequest> b1 = BaseResponse.fromCamelJson(cs, baseRequest.getClass());
    BaseResponse<BaseRequest> b2 = BaseResponse.fromUnderscoreJson(us, baseRequest.getClass());

    System.out.println(b1.toString());
    System.out.println(b2.toString());

    System.out.println(b1.getData().toString());
    System.out.println(b2.getData().toString());
  }
}
