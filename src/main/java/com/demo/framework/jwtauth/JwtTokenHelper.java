package com.demo.framework.jwtauth;

import com.demo.framework.constants.ApiConstants;
import com.demo.framework.constants.enumeration.HttpStatusCodeEnum;
import com.demo.framework.handler.exception.exceptions.ApiException;
import com.demo.framework.helper.TypeHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtTokenHelper
 *
 * <p>JWT Token Tools
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/15/18 - 3:40 PM.
 */
public class JwtTokenHelper {

  private static final long EXPIRE = 60 * 60 * 1000;

  /**
   * @author Created by ivan on 3:33 PM 12/24/18.
   *     <p>//create a token
   * @param claimKey :
   * @param hours :
   * @return java.lang.String
   */
  public static String create(String claimKey, long hours) {
    String secret = ApiConstants.TOKEN_SECRET;
    Date now = new Date();
    Date expire = new Date(now.getTime() + hours * EXPIRE * 1000);
    Map<String, Object> claims = new HashMap<>(2);
    claims.put(ApiConstants.CLAIM_KEY, claimKey);
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expire)
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }
  /**
   * @author Created by ivan on 3:33 PM 12/24/18.
   *     <p>//get Token Clams
   * @param token :
   * @return io.jsonwebtoken.Claims
   */
  static Claims getClams(String token) {
    String secret = ApiConstants.TOKEN_SECRET;
    Claims claims = null;
    try {
      claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    } catch (Exception e) {
      throw new ApiException(HttpStatusCodeEnum.UNAUTHORIZED);
    }
    return claims;
  }

  /**
   * @author Created by ivan on 3:33 PM 12/24/18.
   *     <p>//Get Company Id from Token
   * @param token :
   * @return java.lang.String
   */
  public static String getClaimValue(String token) {
    return TypeHelper.castToString(getClams(token).get(ApiConstants.CLAIM_KEY));
  }
}
