package com.demo.api.demo.access.authinfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.Property;
import com.demo.api.demo.access.authinfo.dao.OpenAuthInfoDao;
import com.demo.api.demo.access.authinfo.model.domain.OpenAuthInfo;
import com.demo.api.demo.access.authinfo.service.OpenAuthInfoService;
import com.demo.api.demo.access.token.model.param.TokenParam;
import com.demo.framework.service.impl.BaseServiceImpl;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * OpenAuthInfoServiceImpl Service implementation
 *
 * @version 1.0
 * @author Created by ivan on 2:24 PM 12/24/18.
 */
@Service
public class OpenAuthInfoServiceImpl extends BaseServiceImpl<OpenAuthInfoDao, OpenAuthInfo>
    implements OpenAuthInfoService {

  /**
   * @author Created by ivan on 2:24 PM 12/24/18.
   *     <p>checkCompanyAuthInfo
   * @param tokenParam :
   * @return java.lang.Boolean
   */
  @Override
  public Boolean checkCompanyAuthInfo(TokenParam tokenParam) {
    Map<Property<OpenAuthInfo, ?>, String> paramMap = new HashMap<>(2);
    paramMap.put(OpenAuthInfo::getAppId, tokenParam.getAppId());
    paramMap.put(OpenAuthInfo::getAppKey, tokenParam.getAppKey());
    LambdaQueryWrapper<OpenAuthInfo> authWrapper =
        new LambdaQueryWrapper<OpenAuthInfo>().select(OpenAuthInfo::getId).allEq(paramMap);
    OpenAuthInfo openAuthInfo = getOne(authWrapper, true);
    return Objects.nonNull(openAuthInfo);
  }

  @Override
  public String getCompanySignKey(String companyId) {
    LambdaQueryWrapper<OpenAuthInfo> getCompanyWrapper =
        new LambdaQueryWrapper<OpenAuthInfo>().eq(OpenAuthInfo::getAppId, companyId);
    return getOne(getCompanyWrapper).getSignKey();
  }
}
