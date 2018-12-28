package com.demo.framework.constants;

/**
 * HttpConstants
 *
 * <p>Http/IP Constants
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/21/18 - 4:48 PM.
 */
public interface HttpConstants {

  /** Min Ip Length */
  Integer IP_MIN_LENGTH = 15;
  /** Proxy Header Char */
  String UNKNOWN = "unknown";
  /** Proxy Header Char */
  String X_REAL_IP = "X-Real-IP";
  /** Proxy Header Char */
  String X_FORWARDED_FOR = "X-Forwarded-For";
  /** Proxy Header Char */
  String PROXY_CLIENT_IP = "Proxy-Client-IP";
  /** Proxy Header Char */
  String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
}
