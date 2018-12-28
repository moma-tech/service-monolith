package com.demo.api.demo.access.token.model.param;

import com.demo.framework.model.domain.SuperModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * TokenParam
 *
 * <p>Token Parameters
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/18/18 - 2:52 PM.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class TokenParam extends SuperModel {

  private static final long serialVersionUID = 867107373133520882L;

  @ApiModelProperty(value = "公司ID", required = true, example = "1")
  @NotBlank(message = "app_id 参数不能为空")
  private String appId;

  @ApiModelProperty(value = "公司Key", required = true, example = "2")
  private String appKey;

  @ApiModelProperty(value = "企业类型", required = false)
  @Min(0)
  private int appType;
}
