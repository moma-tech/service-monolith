package com.demo.api.demo.interception.aspect;

import com.demo.framework.helper.LogHelper;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * LogAspect
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/28/18 - 3:19 PM.
 */
@Aspect
@Component
public class LogAspect {

  @Pointcut(
      "(execution(public * com.demo.api.demo..*Controller.*(..)))||(execution(public * com.demo.api.demo..*Api.*(..)))")
  //
  public void pointCut() {}

  @AfterReturning(returning = "result", pointcut = "pointCut()")
  public void doAfterReturning(Object result) {
    LogHelper.doAfterReturning(result);
  }
}
