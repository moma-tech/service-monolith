package com.moma.service.demo.access.controller;

import com.moma.service.demo.access.model.param.TokenParam;
import com.moma.service.demo.authinfo.service.OpenAuthInfoService;
import com.moma.zoffy.constants.enumeration.HttpStatusCodeEnum;
import com.moma.zoffy.handler.exception.exceptions.ApiException;
import com.moma.zoffy.jwtauth.JwtTokenHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TokenApi
 *
 * <p>Token Api
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/18/18 - 2:43 PM.
 */
@Api(tags = "Token", description = "Access Token Apis")
@RestController
@RequestMapping(value = "/open/token")
public class TokenApi {
  private OpenAuthInfoService openAuthInfoService;

  @Autowired
  public TokenApi(OpenAuthInfoService openAuthInfoService) {
    this.openAuthInfoService = openAuthInfoService;
  }

  /**
   * @author Created by ivan on 2:19 PM 12/24/18.
   *     <p>Obtain Token with Parameters
   * @param tokenParam :
   * @return java.lang.String
   */
  @ApiOperation(value = "Obtain Token", notes = "Obtain An Access Token For Company")
  @PostMapping(
      value = "/request",
      produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String createToken(@RequestBody @Validated TokenParam tokenParam) {
    Boolean check = openAuthInfoService.checkCompanyAuthInfo(tokenParam);
    if (check) {
      String token = JwtTokenHelper.create(tokenParam.getAppId(), 2);
      return token;
    } else {
      throw new ApiException(HttpStatusCodeEnum.UNAUTHORIZED);
    }
  }
}
