package com.demo.api.demo.access.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.api.demo.access.resource.dao.ResourceDao;
import com.demo.api.demo.access.resource.model.domain.Resource;
import com.demo.api.demo.access.resource.service.ResourceService;
import com.demo.api.demo.common.constants.enumeration.ApiTypeEnum;
import com.demo.api.demo.common.model.dto.auth.ResourceAuthDto;
import com.demo.framework.service.impl.BaseServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * ResourceServiceImpl
 *
 * <p>Api Resource Service Implementation
 *
 * @version 1.0
 * @author Created by ivan on 2:55 PM 12/24/18.
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<ResourceDao, Resource>
    implements ResourceService {

  /**
   * @author Created by ivan on 3:02 PM 12/24/18.
   *     <p>//Get Api Resouces with specified HTTP Method and Url Path
   * @param method :
   * @return java.util.List<com.demo.api.demo.common.model.dto.auth.ResourceAuthDto>
   */
  @Override
  public List<ResourceAuthDto> getAuthResources(String method) {
    LambdaQueryWrapper<Resource> resourceWrapper =
        new LambdaQueryWrapper<Resource>()
            .select(Resource::getApiMethod, Resource::getApiPath)
            .eq(Resource::getApiMethod, method);
    return entityList(resourceWrapper, e -> e.beanToBean(ResourceAuthDto.class));
  }

  /**
   * @author Created by ivan on 3:03 PM 12/24/18.
   *     <p>//Get Api Resources with OPEN type
   * @return java.util.List<com.demo.api.demo.common.model.dto.auth.ResourceAuthDto>
   */
  @Override
  public List<ResourceAuthDto> getOpenAuth() {
    LambdaQueryWrapper<Resource> resourceWrapper =
        new LambdaQueryWrapper<Resource>()
            .select(Resource::getApiMethod, Resource::getApiPath)
            .eq(Resource::getApiType, ApiTypeEnum.OPEN.getType());
    return entityList(resourceWrapper, e -> e.beanToBean(ResourceAuthDto.class));
  }

  /**
   * @author Created by ivan on 3:04 PM 12/24/18.
   *     <p>//Get Api Resources with OPEN/TOKEN Type
   * @return java.util.List<com.demo.api.demo.common.model.dto.auth.ResourceAuthDto>
   */
  @Override
  public List<ResourceAuthDto> getNonAuth() {
    Object[] authTypes = new Object[] {ApiTypeEnum.OPEN, ApiTypeEnum.TOKEN};
    LambdaQueryWrapper<Resource> resourceWrapper =
        new LambdaQueryWrapper<Resource>()
            .select(Resource::getApiMethod, Resource::getApiPath)
            .in(Resource::getApiType, authTypes);
    return entityList(resourceWrapper, e -> e.beanToBean(ResourceAuthDto.class));
  }
}
