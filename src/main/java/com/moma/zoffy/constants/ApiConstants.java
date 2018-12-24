package com.moma.zoffy.constants;

/**
 * ApiConstants
 *
 * <p>API Constants
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/15/18 - 4:02 PM.
 */
public interface ApiConstants {

  /* Token Ref. */
  /** Company ID */
  String COMPANY_ID = "appId";
  /** Access Token */
  String ACCESS_TOKEN = "accessToken";
  /** Token RSA Key */
  String TOKEN_SECRET = "1s6U65P4bAay14bMDgHWgtqaTHNTZPZNMDJu3k";

  /* Validating Sign Ref. */
  Long TIMESTAMP_VALID_GAP = 10 * 60 * 1000L;
  String CALCULATE_SIGN_TIMESTAMP = "timestamp=";
  String CALCULATE_SIGN_DATA = "&data=";
  String CALCULATE_SIGN_SIGNKEY = "&sign_key=";

  /* Request Ref. */
  /** Request Start Time */
  String REQUEST_START_TIME = "startTime";
  /** Request Id */
  String REQUEST_ID = "requestId";
  /** Requset URL */
  String REQUEST_URL = "url";
  /** Request Method */
  String REQUEST_METHOD = "method";
  /** Request Mapping */
  String REQUEST_MEPPING = "mapping";
}
