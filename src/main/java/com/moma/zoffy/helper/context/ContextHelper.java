package com.moma.zoffy.helper.context;

import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * ContextHelper
 *
 * <p>Spring Application Context Helper
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/13/18 - 8:09 PM.
 */
public class ContextHelper {
  private static final ApplicationContext APPLICATION_CONTEXT =
      ApplicationContextRegister.getApplicationContext();

  /**
   * @param beanName :
   * @return java.lang.Object
   * @author Created by ivan on 8:32 PM 12/13/18.
   *     <p>//get Bean as Object
   */
  public static Object getBeanObject(String beanName) {
    if (APPLICATION_CONTEXT.containsBean(beanName)) {
      return APPLICATION_CONTEXT.getBean(beanName);
    }
    return null;
  }

  /**
   * @param beanName :
   * @return T
   * @author Created by ivan on 8:33 PM 12/13/18.
   *     <p>//get Bean with type
   */
  public static <T> T getBean(String beanName) {
    if (APPLICATION_CONTEXT.containsBean(beanName)) {
      Class<T> beanType = (Class<T>) APPLICATION_CONTEXT.getType(beanName);
      if (Objects.nonNull(beanType)) {
        return APPLICATION_CONTEXT.getBean(beanName, beanType);
      }
    }
    return null;
  }

  /**
   * @param beanType :
   * @return T
   * @author Created by ivan on 8:33 PM 12/13/18.
   *     <p>//get Bean
   */
  public static <T> T getBean(Class<T> beanType) {
    return APPLICATION_CONTEXT.getBean(beanType);
  }

  /**
   * @return javax.servlet.http.HttpServletRequest
   * @author Created by ivan on 8:33 PM 12/13/18.
   *     <p>//get Servlet Request
   */
  public static HttpServletRequest getRequest() {
    Optional<ServletRequestAttributes> servletRequestAttributes =
        Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    return servletRequestAttributes.map(ServletRequestAttributes::getRequest).orElse(null);
  }
}
