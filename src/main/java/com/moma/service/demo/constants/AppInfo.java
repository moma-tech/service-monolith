package com.moma.service.demo.constants;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AppInfo
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/14/18 - 3:54 PM.
 */
@Component
@ConfigurationProperties(prefix = "appinfo")
@Data
public class AppInfo {
  private String title;
  private String des;
  private String url;
  private String version;
  private String conName;
  private String conUrl;
  private String conEmail;
}
