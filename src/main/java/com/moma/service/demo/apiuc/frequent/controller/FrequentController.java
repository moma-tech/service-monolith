package com.moma.service.demo.apiuc.frequent.controller;

import com.moma.service.demo.apiuc.frequent.model.request.QueryFrequentRequest;
import com.moma.service.demo.apiuc.frequent.model.response.QueryFrequentResponse;
import com.moma.service.demo.constants.BaseUrl;
import com.moma.service.demo.helper.http.ApiCaller;
import com.moma.service.demo.model.base.BaseRequest;
import com.moma.service.demo.model.base.BaseResponseList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FrequentController
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/27/18 - 5:05 PM.
 */
@Api(tags = "UC", value = "第三方常用联系人接口")
@RestController
@RequestMapping(value = "/open/api/third/frequent")
public class FrequentController {

  private static final String QUERY_URL = "/internal/third/frequent/list";

  @ApiOperation(value = "Query Frequent", notes = "Query Frequent For Company")
  @PostMapping(
      value = "/list",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public BaseResponseList<QueryFrequentResponse> queryFrequent(
      @RequestBody BaseRequest<QueryFrequentRequest> queryFrequentRequestBaseRequest) {
    ApiCaller<QueryFrequentRequest, QueryFrequentResponse> apiCaller = new ApiCaller<>();
    return apiCaller.doQueryList(
        BaseUrl.USER_CENTER + QUERY_URL,
        queryFrequentRequestBaseRequest.getData(),
        new QueryFrequentResponse(),
        null);
  }
}
