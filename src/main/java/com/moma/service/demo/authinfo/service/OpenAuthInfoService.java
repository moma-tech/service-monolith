package com.moma.service.demo.authinfo.service;

import com.moma.service.demo.access.model.param.TokenParam;
import com.moma.service.demo.authinfo.model.domain.OpenAuthInfo;
import com.moma.zoffy.service.BaseService;

/**
 * OpenAuthInfoService
 *
 * <p>Service Interface
 *
 * @version 1.0
 * @author Created by ivan on 2:24 PM 12/24/18.
 */
public interface OpenAuthInfoService extends BaseService<OpenAuthInfo> {
  /**
   * @author Created by ivan on 4:43 PM 12/21/18.
   *     <p>Check Company if existed
   * @param tokenParam :
   * @return java.lang.Boolean
   */
  Boolean checkCompanyAuthInfo(TokenParam tokenParam);

  /**
   * @author Created by ivan on 5:29 PM 12/24/18.
   *     <p>get Company Sign Key
   * @param companyId :
   * @return java.lang.String
   */
  String getCompanySignKey(String companyId);
}
