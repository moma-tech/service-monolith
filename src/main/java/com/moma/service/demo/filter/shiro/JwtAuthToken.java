package com.moma.service.demo.filter.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * JwtAuthToken
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/15/18 - 3:39 PM.
 */
public class JwtAuthToken implements AuthenticationToken {

  private static final long serialVersionUID = -1776388335670867876L;
  private String token;

  public JwtAuthToken(String token) {
    this.token = token;
  }

  @Override
  public Object getPrincipal() {
    return token;
  }

  @Override
  public Object getCredentials() {
    return token;
  }
}
