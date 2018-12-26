package com.moma.service.demo.filter;

import com.moma.service.demo.model.dto.auth.ResourceAuthDto;
import com.moma.service.demo.resource.service.ResourceService;
import com.moma.zoffy.constants.ApiConstants;
import com.moma.zoffy.constants.enumeration.HttpStatusCodeEnum;
import com.moma.zoffy.helper.RequestHelper;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.PathMatcher;
import org.springframework.web.util.UrlPathHelper;

/**
 * AuthFilter
 *
 * <p>Basic Auth Filter with JWT Token
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/17/18 - 7:28 PM.
 */
public class BasicAuthFilter implements Filter {
  @Autowired private PathMatcher pathMatcher;
  @Autowired private ResourceService resourceService;
  @Autowired private UrlPathHelper urlPathHelper;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  /**
   * @author Created by ivan on 2:37 PM 12/24/18.
   *     <p>Fliter Auth info
   *     <p>1.add log info
   *     <p>2.match resource user can access
   * @param servletRequest : Request
   * @param servletResponse : Response
   * @param filterChain : F chain
   */
  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    String requestId = ObjectId.get().toString();
    servletRequest.setAttribute(ApiConstants.REQUEST_ID, requestId);
    servletRequest.setAttribute(ApiConstants.REQUEST_START_TIME, System.currentTimeMillis());
    MDC.put(ApiConstants.REQUEST_ID, requestId);

    HttpServletRequest httpRequest = (HttpServletRequest) (servletRequest);
    HttpServletResponse httpResponse = (HttpServletResponse) (servletResponse);

    String token = RequestHelper.getToken(httpRequest);
    String method = httpRequest.getMethod();
    String requestUri = urlPathHelper.getOriginatingRequestUri(httpRequest);

    servletRequest.setAttribute(ApiConstants.REQUEST_URL, requestUri);
    servletRequest.setAttribute(ApiConstants.REQUEST_METHOD, method);

    List<ResourceAuthDto> aaa = resourceService.getAuthResources(method);
    Optional<ResourceAuthDto> resourceAuthDto =
        resourceService
            .getAuthResources(method)
            .stream()
            .filter(match(method, requestUri))
            .findFirst();
    /* Check if resource exist */
    if (resourceAuthDto.isPresent()) {
      servletRequest.setAttribute(ApiConstants.REQUEST_MEPPING, resourceAuthDto.get().getApiPath());
    } else {
      httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
      sendNotFoundFail(servletRequest, servletResponse);
      return;
    }
    /* Check if Token exist */
    if (Objects.isNull(token)) {
      List<ResourceAuthDto> resourceAuthDtoList = resourceService.getOpenAuth();
      if (!anyMatch(resourceAuthDtoList, method, requestUri)) {
        sendUnauthorizedFail(servletRequest, servletResponse);
        return;
      }
    } else {
      /* Check if Company exist */
      String companyId = JwtTokenHelper.getClaimValue(token);
      if (StringUtils.isBlank(companyId)) {
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        sendUnauthorizedFail(servletRequest, servletResponse);
        return;
      }
      servletRequest.setAttribute(ApiConstants.CLAIM_KEY, companyId);
      /* Check if a OPEN/TOKEN Auth */
      List<ResourceAuthDto> resourceAuthDtoList = resourceService.getNonAuth();
      if (!anyMatch(resourceAuthDtoList, method, requestUri)) {
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        sendUnauthorizedFail(servletRequest, servletResponse);
        return;
      }
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }
  /**
   * @author Created by ivan on 2:44 PM 12/24/18.
   *     <p>//remove MDC
   */
  @Override
  public void destroy() {
    MDC.remove(ApiConstants.REQUEST_ID);
  }

  /**
   * @author Created by ivan on 2:39 PM 12/24/18.
   *     <p>Match HTTP Method / Request URL
   * @param method : HTTP Method Head
   * @param requestUri : Request URL
   * @return java.util.function.Predicate<com.moma.service.demo.model.dto.auth.ResourceAuthDto>
   */
  private Predicate<ResourceAuthDto> match(String method, String requestUri) {
    return res ->
        res.getApiMethod().equalsIgnoreCase(method)
            && pathMatcher.match(res.getApiPath(), requestUri);
  }

  /**
   * @author Created by ivan on 2:45 PM 12/24/18.
   *     <p>//Match Any in List with Method/Request URL
   * @param perms : resource List
   * @param method : HTTP Method
   * @param requestUri : Request URL
   * @return boolean : T/F
   */
  private boolean anyMatch(List<ResourceAuthDto> perms, String method, String requestUri) {
    return perms.stream().anyMatch(match(method, requestUri));
  }

  /**
   * @author Created by ivan on 2:47 PM 12/24/18.
   *     <p>//Response back with Forbidden
   * @param request : request
   * @param response : response
   */
  private void sendForbiddenFail(ServletRequest request, ServletResponse response) {
    ResponseHelper.response(
        (HttpServletRequest) (request),
        (HttpServletResponse) (response),
        HttpStatusCodeEnum.FORBIDDEN);
  }

  /**
   * @author Created by ivan on 2:48 PM 12/24/18.
   *     <p>//Not match a resource
   * @param request : request
   * @param response : response
   */
  private void sendNotFoundFail(ServletRequest request, ServletResponse response) {
    ResponseHelper.response(
        (HttpServletRequest) (request),
        (HttpServletResponse) (response),
        HttpStatusCodeEnum.NOT_FOUND);
  }

  /**
   * @author Created by ivan on 2:48 PM 12/24/18.
   *     <p>//Not Auth for the resource
   * @param request : request
   * @param response : response
   */
  private void sendUnauthorizedFail(ServletRequest request, ServletResponse response) {
    ResponseHelper.response(
        (HttpServletRequest) (request),
        (HttpServletResponse) (response),
        HttpStatusCodeEnum.UNAUTHORIZED);
  }
}
