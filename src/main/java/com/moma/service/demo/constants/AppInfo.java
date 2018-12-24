package com.moma.service.demo.constants;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AppInfo
 *
 * <p>Api Info for Swagger2
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/14/18 - 3:54 PM.
 */
@Component
@ConfigurationProperties(prefix = "appinfo")
@Data
public class AppInfo {
  @ApiModelProperty(value = "项目名称")
  private String title;

  @ApiModelProperty(value = "项目描述")
  private String des;

  @ApiModelProperty(value = "项目URL")
  private String url;

  @ApiModelProperty(value = "版本号")
  private String version;

  @ApiModelProperty(value = "负责人")
  private String conName;

  @ApiModelProperty(value = "相关URL")
  private String conUrl;

  @ApiModelProperty(value = "联系EMail")
  private String conEmail;
}
