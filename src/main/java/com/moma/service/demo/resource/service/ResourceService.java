package com.moma.service.demo.resource.service;

import com.moma.service.demo.model.dto.auth.ResourceAuthDto;
import com.moma.service.demo.resource.model.domain.Resource;
import com.moma.zoffy.service.BaseService;
import java.util.List;

/**
 * 服务类
 *
 * @author Ivan
 * @since 2018-12-15
 */
public interface ResourceService extends BaseService<Resource> {

  public List<ResourceAuthDto> getAuthResources(String method);

  public List<ResourceAuthDto> getOpenAuth();

  public List<ResourceAuthDto> getNonAuth();
}
