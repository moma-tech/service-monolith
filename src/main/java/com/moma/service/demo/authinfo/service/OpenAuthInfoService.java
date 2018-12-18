package com.moma.service.demo.authinfo.service;

import com.moma.service.demo.access.model.param.TokenParam;
import com.moma.service.demo.authinfo.model.domain.OpenAuthInfo;
import com.moma.zoffy.service.BaseService;

/**
 * 服务类
 *
 * @author Ivan
 * @since 2018-12-17
 */
public interface OpenAuthInfoService extends BaseService<OpenAuthInfo> {
  Boolean checkCompanyAuthInfo(TokenParam tokenParam);
}
