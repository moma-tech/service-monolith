package com.moma.service.demo.config;

import com.moma.service.demo.constants.AppInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfiguration
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/14/18 - 3:49 PM.
 */
@SpringBootConfiguration
@EnableSwagger2
public class SwaggerConfiguration {

  private final AppInfo appInfo;

  @Autowired
  public SwaggerConfiguration(AppInfo appInfo) {
    this.appInfo = appInfo;
  }

  /**
   * 获取
   *
   * @return
   */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(appInfo.getTitle())
        .description(appInfo.getDes())
        .termsOfServiceUrl(appInfo.getUrl())
        .version(appInfo.getVersion())
        .contact(new Contact(appInfo.getConName(), appInfo.getUrl(), appInfo.getConEmail()))
        .build();
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(this.apiInfo())
        .select()
        .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
        .paths(PathSelectors.any())
        .build();
  }
}
