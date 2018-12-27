package com.moma.service.demo.apiuc.frequent.model.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * QueryFrequentRequest
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/27/18 - 5:11 PM.
 */
@Data
public class QueryFrequentRequest {
  @ApiModelProperty(value = "企业ID", example = "5747fbc10f0e60e0709d8d7d", required = true)
  @NotNull
  private String companyId;

  @ApiModelProperty(value = "当前登录用户ID", example = "59bf74f423445f31bd64bc5c", required = true)
  private String ownerId;

  @ApiModelProperty(
      value = "员工ID  当前组织架构员工ID（type为1必填)",
      example = "59bf74f423445f31bd64bc5c",
      required = true)
  private String selectedEmployeeId;

  @ApiModelProperty(value = "类型    1:分贝企业    2:第三方企业", example = "1", required = true)
  private String type;

  @ApiModelProperty(value = "1：国内   2：国际   不传默认1", example = "1", required = true)
  private String frequentType;
}
