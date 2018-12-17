package com.moma.zoffy.response.dto;

import com.moma.zoffy.response.Response;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * FailedResponse
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/14/18 - 5:18 PM.
 */
@Getter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
public class FailedResponse extends Response implements java.io.Serializable {

  private static final long serialVersionUID = -9037458751252134695L;
  private Integer code;

  private String msg;

  private String errorMsg;

  private String info;

  private LocalDateTime time;
}
