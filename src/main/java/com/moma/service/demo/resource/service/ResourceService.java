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
  /**
   * @author Created by ivan on 4:45 PM 12/21/18.
   *     <p>//TODO getAuthResources
   * @param method :
   * @return java.util.List<com.moma.service.demo.model.dto.auth.ResourceAuthDto>
   */
  List<ResourceAuthDto> getAuthResources(String method);
  /**
   * @author Created by ivan on 4:45 PM 12/21/18.
   *     <p>//TODO getOpenAuth
   * @return java.util.List<com.moma.service.demo.model.dto.auth.ResourceAuthDto>
   */
  List<ResourceAuthDto> getOpenAuth();
  /**
   * @author Created by ivan on 4:45 PM 12/21/18.
   *     <p>//TODO getNonAuth
   * @return java.util.List<com.moma.service.demo.model.dto.auth.ResourceAuthDto>
   */
  List<ResourceAuthDto> getNonAuth();
}
