package com.moma.service.demo.controller.demo;

import com.moma.zoffy.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoApi
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/14/18 - 4:29 PM.
 */
@Api(tags = "demo", description = "Demo")
@RestController
@RequestMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DemoApi extends BaseController {

  @PostMapping("/test")
  @ApiOperation(value = "Test", notes = "this is test")
  public String test(String a, String b) {
    return a + b;
  }
}
