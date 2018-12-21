package com.moma.zoffy.helper;

import com.moma.zoffy.constants.HttpConstants;
import com.moma.zoffy.constants.SysConstants;
import com.moma.zoffy.constants.enumeration.HttpMethodEnum;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

/**
 * RequestHelper
 *
 * <p>Request Helper
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/13/18 - 7:34 PM.
 */
@Slf4j
public class RequestHelper {

  /**
   * @param request :
   * @return java.lang.String
   * @author Created by ivan on 8:00 PM 12/13/18.
   *     <p>Output Request Body
   */
  public static String getRequestBody(HttpServletRequest request) {
    String requestBody = null;
    if (isContainsBody(request)) {
      try {
        requestBody = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
      } catch (IOException ignore) {
      }
    }
    return requestBody;
  }
  /**
   * @author Created by ivan on 3:19 PM 12/20/18.
   *     <p>getByteBody
   * @param request :
   * @return byte[]
   */
  public static byte[] getByteBody(HttpServletRequest request) {
    byte[] body = new byte[0];
    try {
      body = StreamUtils.copyToByteArray(request.getInputStream());
    } catch (IOException e) {
      log.error("Error: Get RequestBody byte[] fail," + e);
    }
    return body;
  }
  /**
   * @param request :
   * @return boolean
   * @author Created by ivan on 7:59 PM 12/13/18.
   *     <p>Confirm isContainsBody
   */
  public static boolean isContainsBody(HttpServletRequest request) {
    return isPost(request) || isPut(request) || isPatch(request);
  }

  /**
   * @param request :
   * @return boolean
   * @author Created by ivan on 7:58 PM 12/13/18.
   *     <p>Confirm isPost
   */
  public static boolean isPost(HttpServletRequest request) {
    return HttpMethodEnum.POST.toString().equalsIgnoreCase(request.getMethod());
  }

  /**
   * @param request :
   * @return boolean
   * @author Created by ivan on 7:58 PM 12/13/18.
   *     <p>Confirm isPut
   */
  public static boolean isPut(HttpServletRequest request) {
    return HttpMethodEnum.PUT.toString().equalsIgnoreCase(request.getMethod());
  }

  /**
   * @param request :
   * @return boolean
   * @author Created by ivan on 7:58 PM 12/13/18.
   *     <p>Confirm isPatch
   */
  public static boolean isPatch(HttpServletRequest request) {
    return HttpMethodEnum.PATCH.toString().equalsIgnoreCase(request.getMethod());
  }

  /**
   * @param request :
   * @return boolean
   * @author Created by ivan on 7:59 PM 12/13/18.
   *     <p>=Confirm isGet
   */
  public static boolean isGet(HttpServletRequest request) {
    return HttpMethodEnum.GET.toString().equalsIgnoreCase(request.getMethod());
  }

  /**
   * @param request :
   * @return boolean
   * @author Created by ivan on 7:59 PM 12/13/18.
   *     <p>Confirm isDelete
   */
  public static boolean isDelete(HttpServletRequest request) {
    return HttpMethodEnum.DELETE.toString().equalsIgnoreCase(request.getMethod());
  }

  /**
   * @param request :
   * @return boolean
   * @author Created by ivan on 7:59 PM 12/13/18.
   *     <p>Confirm isTrace
   */
  public static boolean isTrace(HttpServletRequest request) {
    return HttpMethodEnum.TRACE.toString().equalsIgnoreCase(request.getMethod());
  }

  /**
   * @param request :
   * @return boolean
   * @author Created by ivan on 7:59 PM 12/13/18.
   *     <p>Confirm isHead
   */
  public static boolean isHead(HttpServletRequest request) {
    return HttpMethodEnum.HEAD.toString().equalsIgnoreCase(request.getMethod());
  }

  /**
   * @param request :
   * @return boolean
   * @author Created by ivan on 7:59 PM 12/13/18.
   *     <p>Confirm isOptions
   */
  public static boolean isOptions(HttpServletRequest request) {
    return HttpMethodEnum.OPTIONS.toString().equalsIgnoreCase(request.getMethod());
  }

  /**
   * @param request :
   * @return java.lang.String
   * @author Created by ivan on 8:05 PM 12/13/18.
   *     <p>//get Ip Addr
   */
  public static String getIpAddr(HttpServletRequest request) {
    // nginx代理获取的真实用户ip
    String ip = request.getHeader(HttpConstants.X_REAL_IP);
    if (StringUtils.isBlank(ip) || HttpConstants.UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader(HttpConstants.X_FORWARDED_FOR);
    }
    if (StringUtils.isBlank(ip) || HttpConstants.UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader(HttpConstants.PROXY_CLIENT_IP);
    }
    if (StringUtils.isBlank(ip) || HttpConstants.UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader(HttpConstants.WL_PROXY_CLIENT_IP);
    }
    if (StringUtils.isBlank(ip) || HttpConstants.UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }

    if (null != ip && ip.length() > HttpConstants.IP_MIN_LENGTH) {
      if (ip.indexOf(SysConstants.GLOBE_SPLIT_COMMA) > 0) {
        ip = ip.substring(0, ip.indexOf(SysConstants.GLOBE_SPLIT_COMMA));
      }
    }
    return ip;
  }
}
