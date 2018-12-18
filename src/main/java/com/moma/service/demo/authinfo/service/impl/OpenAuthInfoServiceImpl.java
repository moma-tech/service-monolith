package com.moma.service.demo.authinfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.Property;
import com.moma.service.demo.access.model.param.TokenParam;
import com.moma.service.demo.authinfo.dao.OpenAuthInfoDao;
import com.moma.service.demo.authinfo.model.domain.OpenAuthInfo;
import com.moma.service.demo.authinfo.service.OpenAuthInfoService;
import com.moma.zoffy.service.impl.BaseServiceImpl;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author Ivan
 * @since 2018-12-17
 */
@Service
public class OpenAuthInfoServiceImpl extends BaseServiceImpl<OpenAuthInfoDao, OpenAuthInfo>
    implements OpenAuthInfoService {

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
}
