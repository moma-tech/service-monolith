package com.moma.service.demo.access.model.param;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * TokenParam
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/18/18 - 2:52 PM.
 */
@Data
@AllArgsConstructor
@ToString
public class TokenParam {

  @ApiModelProperty(value = "公司ID", required = true, example = "1")
  @NotBlank(message = "app_id 参数不能为空")
  private String appId;

  @ApiModelProperty(value = "公司Key", required = true, example = "2")
  private String appKey;

  @ApiModelProperty(value = "企业类型", required = false)
  @Min(0)
  private int appType;
}
