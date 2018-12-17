package com.moma.service.demo.filter;

import com.moma.service.demo.model.dto.auth.ResourceAuthDto;
import com.moma.service.demo.resource.service.ResourceService;
import com.moma.zoffy.constants.ApiConstants;
import com.moma.zoffy.constants.SysConstants;
import com.moma.zoffy.constants.enumeration.HttpStatusCodeEnum;
import com.moma.zoffy.helper.ResponseHelper;
import com.moma.zoffy.jwtauth.JwtTokenHelper;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.util.PathMatcher;
import org.springframework.web.util.UrlPathHelper;

/**
 * AuthFilter
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/17/18 - 7:28 PM.
 */
@Order(1)
@WebFilter(filterName = "basicAuthFilter", urlPatterns = "/")
public class BasicAuthFilter implements Filter {

  private PathMatcher pathMatcher;
  private ResourceService resourceService;
  private UrlPathHelper urlPathHelper;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    servletRequest.setAttribute(ApiConstants.REQUEST_START_TIME, System.currentTimeMillis());
    HttpServletRequest httpRequest = (HttpServletRequest) (servletRequest);
    HttpServletResponse httpResponse = (HttpServletResponse) (servletResponse);

    String token = getToken(httpRequest);
    String method = httpRequest.getMethod();
    String requestUri = urlPathHelper.getOriginatingRequestUri(httpRequest);

    servletRequest.setAttribute(ApiConstants.REQUEST_URL, requestUri);
    servletRequest.setAttribute(ApiConstants.REQUEST_METHOD, method);

    Optional<ResourceAuthDto> resourceAuthDto =
        resourceService
            .getAuthResources(method)
            .stream()
            .filter(match(method, requestUri))
            .findFirst();
    /** Check if resource exist */
    if (resourceAuthDto.isPresent()) {
      servletRequest.setAttribute(ApiConstants.REQUEST_MEPPING, resourceAuthDto.get().getMapping());
    } else {
      httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
      sendNotFoundFail(servletRequest, servletResponse);
      return;
    }
    /** Check if Token exist */
    if (Objects.isNull(token)) {
      List<ResourceAuthDto> resourceAuthDtoList = resourceService.getOpenAuth();
      if (!anyMatch(resourceAuthDtoList, method, requestUri)) {
        sendUnauthorizedFail(servletRequest, servletResponse);
        return;
      }
    } else {
      String companyId = JwtTokenHelper.getCompanyId(token, ApiConstants.TOKEN_SECRET);
      if (StringUtils.isBlank(companyId)) {
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        sendUnauthorizedFail(servletRequest, servletResponse);
        return;
      }
      servletRequest.setAttribute(ApiConstants.COMPANY_ID, companyId);
      List<ResourceAuthDto> resourceAuthDtoList = resourceService.getNonAuth();
      if (!anyMatch(resourceAuthDtoList, method, requestUri)) {
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        sendUnauthorizedFail(servletRequest, servletResponse);
        return;
      }
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {}

  private String getToken(HttpServletRequest httpRequest) {
    String token = httpRequest.getHeader(SysConstants.AUTHORIZATION_HEADER);
    if (StringUtils.isBlank(token)) {
      token = httpRequest.getParameter(ApiConstants.ACCESS_TOKEN);
    }
    return StringUtils.isBlank(token) ? null : token.replaceFirst("Bearer", "");
  }

  private Predicate<ResourceAuthDto> match(String method, String requestUri) {
    return res ->
        res.getMethod().equalsIgnoreCase(method) && pathMatcher.match(res.getMapping(), requestUri);
  }

  protected boolean anyMatch(List<ResourceAuthDto> perms, String method, String requestUri) {
    return perms.stream().anyMatch(match(method, requestUri));
  }

  /** 无权限 */
  protected boolean sendForbiddenFail(ServletRequest request, ServletResponse response) {
    ResponseHelper.response(
        (HttpServletRequest) (request),
        (HttpServletResponse) (response),
        HttpStatusCodeEnum.FORBIDDEN);
    return false;
  }

  /** 路径不存在 */
  protected boolean sendNotFoundFail(ServletRequest request, ServletResponse response) {
    ResponseHelper.response(
        (HttpServletRequest) (request),
        (HttpServletResponse) (response),
        HttpStatusCodeEnum.NOT_FOUND);
    return false;
  }

  /** 未认证 */
  protected boolean sendUnauthorizedFail(ServletRequest request, ServletResponse response) {
    ResponseHelper.response(
        (HttpServletRequest) (request),
        (HttpServletResponse) (response),
        HttpStatusCodeEnum.UNAUTHORIZED);
    return false;
  }
}