package com.moma.zoffy.helper.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextRegister
 *
 * <p>Application Context Getter
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/13/18 - 8:07 PM.
 */
@Component
public class ApplicationContextRegister implements ApplicationContextAware {
  private static ApplicationContext APPLICATION_CONTEXT;

  static ApplicationContext getApplicationContext() {
    return APPLICATION_CONTEXT;
  }

  /**
   * 设置spring上下文
   *
   * @param applicationContext spring上下文
   */
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    APPLICATION_CONTEXT = applicationContext;
  }
}
