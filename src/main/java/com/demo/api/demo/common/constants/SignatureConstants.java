package com.demo.api.demo.common.constants;

/**
 * SignatureConstants
 *
 * <p>Signature Calculation Constants
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/26/18 - 6:05 PM.
 */
public interface SignatureConstants {
  /* Validating Sign Ref. */
  Long TIMESTAMP_VALID_GAP = 10 * 60 * 1000L;

  String CALCULATE_SIGN_TIMESTAMP = "timestamp=";
  String CALCULATE_SIGN_DATA = "&data=";
  String CALCULATE_SIGN_SIGNKEY = "&sign_key=";
}
