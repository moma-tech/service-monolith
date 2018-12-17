package com.moma.zoffy.response.dto;

import com.moma.zoffy.response.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * SuccessResponse
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/14/18 - 5:56 PM.
 */
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SuccessResponse<T> extends Response<T> implements java.io.Serializable {

  private static final long serialVersionUID = 4074598966911131015L;

  private Integer code;
  private String msg;
  private T result;
}
