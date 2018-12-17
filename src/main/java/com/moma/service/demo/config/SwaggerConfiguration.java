package com.moma.service.demo.config;

import io.swagger.annotations.ApiOperation;
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

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false);
  }

  /**
   * 获取
   *
   * @return
   */
  private static ApiInfo apiInfo() {
/*
    String title = appInfo.getTitle();
    String description = appInfo.getDes();
    String serviceUrl = appInfo.getUrl();
    String version = appInfo.getVersion();
    String contactName = appInfo.getConName();
    String contactUrl = appInfo.getConUrl();
    String contactEmail = appInfo.getConEmail();
*/

    return new ApiInfoBuilder()
        .title("123")
        .description("123")
        .termsOfServiceUrl("!23")
        .version("123")
        .contact(new Contact("123", ":#21", "qwe"))
        .build();
  }
}
