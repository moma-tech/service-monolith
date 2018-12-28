package com.demo.api.demo.access.resource.service;

import com.demo.api.demo.access.resource.model.domain.Resource;
import com.demo.api.demo.common.model.dto.auth.ResourceAuthDto;
import com.demo.framework.service.BaseService;
import java.util.List;

/**
 * ResourceService
 *
 * <p>Api Resource Service Interface
 *
 * @version 1.0
 * @author Created by ivan on 2:55 PM 12/24/18.
 */
public interface ResourceService extends BaseService<Resource> {
  /**
   * @author Created by ivan on 4:45 PM 12/21/18.
   *     <p>//Get Api Resource with Method and URL
   * @param method :
   * @return java.util.List<com.moma.service.demo.model.dto.auth.ResourceAuthDto>
   */
  List<ResourceAuthDto> getAuthResources(String method);
  /**
   * @author Created by ivan on 4:45 PM 12/21/18.
   *     <p>//Get Api Resources with OPEN TYpe
   * @return java.util.List<com.moma.service.demo.model.dto.auth.ResourceAuthDto>
   */
  List<ResourceAuthDto> getOpenAuth();
  /**
   * @author Created by ivan on 4:45 PM 12/21/18.
   *     <p>//Get Api Resources with OPEN/TOKEN Type
   * @return java.util.List<com.moma.service.demo.model.dto.auth.ResourceAuthDto>
   */
  List<ResourceAuthDto> getNonAuth();
}
