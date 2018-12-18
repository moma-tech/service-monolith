package com.moma.service.demo.model.dto.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ResourceAuthDto
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/15/18 - 5:12 PM.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResourceAuthDto {
  @ApiModelProperty(notes = "Request HTTP Method")
  private String apiMethod;

  @ApiModelProperty(notes = "Request Path Mapping")
  private String apiPath;
}
