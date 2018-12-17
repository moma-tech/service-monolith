package com.moma.service.demo.filter.shiro;

import com.moma.service.demo.model.dto.auth.ResourceAuthDto;
import com.moma.service.demo.resource.service.ResourceService;
import com.moma.zoffy.constants.ApiConstants;
import com.moma.zoffy.constants.enumeration.HttpStatusCodeEnum;
import com.moma.zoffy.helper.ResponseHelper;
import com.moma.zoffy.jwtauth.JwtTokenHelper;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.PathMatcher;
import org.springframework.web.util.UrlPathHelper;

/**
 * JwtAuthFilter
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/15/18 - 3:36 PM.
 */
public class JwtAuthFilter extends BasicHttpAuthenticationFilter {

  private PathMatcher pathMatcher;
  private UrlPathHelper urlPathHelper;
  private ResourceService resourceService;

  @Override
  protected AuthenticationToken createToken(
      ServletRequest servletRequest, ServletResponse servletResponse) {
    // 获取请求token
    String token = getToken(WebUtils.toHttp(servletRequest));
    if (StringUtils.isBlank(token)) {
      return null;
    }
    return StringUtils.isBlank(token) ? null : new JwtAuthToken(token);
  }

  @Override
  protected boolean isAccessAllowed(
      ServletRequest request, ServletResponse response, Object mappedValue) {
    request.setAttribute(ApiConstants.REQUEST_START_TIME, System.currentTimeMillis());
    HttpServletRequest httpRequest = WebUtils.toHttp(request);
    HttpServletResponse httpResponse = WebUtils.toHttp(response);

    String token = getToken(httpRequest);
    String method = httpRequest.getMethod();
    String requestUri = urlPathHelper.getOriginatingRequestUri(httpRequest);

    request.setAttribute(ApiConstants.REQUEST_URL, requestUri);
    request.setAttribute(ApiConstants.REQUEST_METHOD, method);

    Optional<ResourceAuthDto> resourceAuthDto =
        resourceService
            .getAuthResources(method)
            .stream()
            .filter(match(method, requestUri))
            .findFirst();

    if (resourceAuthDto.isPresent()) {
      request.setAttribute(ApiConstants.REQUEST_MEPPING, resourceAuthDto.get().getMapping());
    } else {
      httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return sendNotFoundFail(request, response);
    }

    if (Objects.isNull(token)) {
      List<ResourceAuthDto> resourceAuthDtoList = resourceService.getOpenAuth();
      return anyMatch(resourceAuthDtoList, method, requestUri);
    } else {
      String companyId = JwtTokenHelper.getCompanyId(token, ApiConstants.TOKEN_SECRET);
      if (StringUtils.isBlank(companyId)) {
        return sendUnauthorizedFail(request, response);
      }
      request.setAttribute(ApiConstants.COMPANY_ID, companyId);
      List<ResourceAuthDto> resourceAuthDtoList = resourceService.getNonAuth();
      return anyMatch(resourceAuthDtoList, method, requestUri);
    }
  }

  @Override
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
    HttpServletResponse httpResponse = WebUtils.toHttp(response);
    switch (httpResponse.getStatus()) {
      case HttpServletResponse.SC_NOT_FOUND:
        return sendNotFoundFail(request, response);
      case HttpServletResponse.SC_UNAUTHORIZED:
        return sendUnauthorizedFail(request, response);
      default:
        return sendForbiddenFail(request, response);
    }
  }

  protected String getToken(HttpServletRequest request) {
    String token = request.getHeader(AUTHORIZATION_HEADER);
    if (StringUtils.isBlank(token)) {
      token = request.getParameter(ApiConstants.ACCESS_TOKEN);
    }
    return StringUtils.isBlank(token) ? null : token.replaceFirst("Bearer ", "");
  }

  /** 无权限 */
  protected boolean sendForbiddenFail(ServletRequest request, ServletResponse response) {
    ResponseHelper.response(
        WebUtils.toHttp(request), WebUtils.toHttp(response), HttpStatusCodeEnum.FORBIDDEN);
    return false;
  }

  /** 路径不存在 */
  protected boolean sendNotFoundFail(ServletRequest request, ServletResponse response) {
    ResponseHelper.response(
        WebUtils.toHttp(request), WebUtils.toHttp(response), HttpStatusCodeEnum.NOT_FOUND);
    return false;
  }

  /** 未认证 */
  protected boolean sendUnauthorizedFail(ServletRequest request, ServletResponse response) {
    ResponseHelper.response(
        WebUtils.toHttp(request), WebUtils.toHttp(response), HttpStatusCodeEnum.UNAUTHORIZED);
    return false;
  }

  private Predicate<ResourceAuthDto> match(String method, String requestUri) {
    return res ->
        res.getMethod().equalsIgnoreCase(method) && pathMatcher.match(res.getMapping(), requestUri);
  }

  protected boolean anyMatch(List<ResourceAuthDto> perms, String method, String requestUri) {
    return perms.stream().anyMatch(match(method, requestUri));
  }
}
