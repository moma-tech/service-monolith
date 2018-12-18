package com.moma.zoffy.jwtauth;

import com.moma.zoffy.constants.ApiConstants;
import com.moma.zoffy.constants.enumeration.HttpStatusCodeEnum;
import com.moma.zoffy.handler.exception.exceptions.ApiException;
import com.moma.zoffy.helper.TypeHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtTokenHelper
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/15/18 - 3:40 PM.
 */
public class JwtTokenHelper {

  private static final long EXPIRE = 60 * 60 * 1000;

  public static String create(String companyId, long hours, String secret) {
    Date now = new Date();
    Date expire = new Date(now.getTime() + hours * EXPIRE * 1000);
    Map<String, Object> claims = new HashMap<>(2);
    claims.put(ApiConstants.COMPANY_ID, companyId);
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expire)
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }

  static Claims getClams(String token, String secret) {
    Claims claims = null;
    try {
      claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    } catch (Exception e) {
      throw new ApiException(HttpStatusCodeEnum.UNAUTHORIZED);
    }
    return claims;
  }

  public static String getCompanyId(String token, String secret) {
    return TypeHelper.castToString(getClams(token, secret).get(ApiConstants.COMPANY_ID));
  }
}
