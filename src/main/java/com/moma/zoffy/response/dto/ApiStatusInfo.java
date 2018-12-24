package com.moma.zoffy.response.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * HttpStatusInfo
 *
 * <p>Object to Hold Http Status
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/14/18 - 5:37 PM.
 */
@Data
@AllArgsConstructor
@Builder
public class ApiStatusInfo {
  /** Code */
  private int code;
  /** Message */
  private String msg;
}
