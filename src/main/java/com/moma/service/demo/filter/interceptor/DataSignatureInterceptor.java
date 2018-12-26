package com.moma.service.demo.filter.interceptor;

import com.moma.service.demo.authinfo.service.OpenAuthInfoService;
import com.moma.service.demo.constants.SignatureConstants;
import com.moma.service.demo.model.base.BaseRequest;
import com.moma.zoffy.constants.ApiConstants;
import com.moma.zoffy.constants.enumeration.ApiStatusCodeEnum;
import com.moma.zoffy.handler.exception.exceptions.ApiException;
import com.moma.zoffy.helper.JacksonHelper;
import com.moma.zoffy.helper.RequestHelper;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * DataSignatureInterceptor
 *
 * <p>Data Signature Verification Interceptor
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/24/18 - 6:17 PM.
 */
public class DataSignatureInterceptor extends HandlerInterceptorAdapter {
  @Autowired OpenAuthInfoService openAuthInfoService;

  @Override
  public boolean preHandle(
      HttpServletRequest httpRequest, HttpServletResponse response, Object handler)
      throws Exception {
    String token = RequestHelper.getToken(httpRequest);
    /* If No Token, is a Open One, no need check*/
    if (StringUtils.isNotBlank(token)) {
      /* Read Sign Key From DB */
      String companyId = (String) httpRequest.getAttribute(ApiConstants.CLAIM_KEY);
      String signKey = openAuthInfoService.getCompanySignKey(companyId);

      /* Read Income Msg from Request */
      String requestBody = RequestHelper.getRequestBody(httpRequest);
      BaseRequest params = JacksonHelper.readValue(requestBody, BaseRequest.class);

      /* Valid Timestamp */
      if (Objects.isNull(params.getTimestamp())) {
        throw new ApiException(ApiStatusCodeEnum.UN_TIMESTAMP.transform());
      } else {
        if (System.currentTimeMillis()
            > (params.getTimestamp() + SignatureConstants.TIMESTAMP_VALID_GAP)) {
          throw new ApiException(ApiStatusCodeEnum.UN_VALID_TIMESTAMP.transform());
        }
      }
      /* UnNull Data */
      if (Objects.isNull(params.getData())) {
        throw new ApiException(ApiStatusCodeEnum.UN_DATA.transform());
      }

      /* UnNull Signature */
      if (StringUtils.isBlank(params.getSign())) {
        throw new ApiException(ApiStatusCodeEnum.UN_SIGN.transform());
      }

      /* Valid Signature */
      String calculateSign =
          DigestUtils.md5DigestAsHex(
              (SignatureConstants.CALCULATE_SIGN_TIMESTAMP
                      + String.valueOf(params.getTimestamp())
                      + SignatureConstants.CALCULATE_SIGN_DATA
                      + params.presentJsonData()
                      + SignatureConstants.CALCULATE_SIGN_SIGNKEY
                      + signKey)
                  .getBytes(StandardCharsets.UTF_8));
      if (!calculateSign.equalsIgnoreCase(params.getSign())) {
        throw new ApiException(ApiStatusCodeEnum.SIGN_ERROR.transform());
      }
    }
    /* Passed */
    return true;
  }
}
