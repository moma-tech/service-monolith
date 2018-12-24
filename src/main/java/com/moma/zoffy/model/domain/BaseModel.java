package com.moma.zoffy.model.domain;

import com.moma.zoffy.helper.modelmapper.BeanHelper;

/**
 * BaseModel
 *
 * <p>Base Model
 *
 * <p>Each Model should extends this
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/13/18 - 7:26 PM.
 */
public class BaseModel {

  public <T> T beanToBean(Class<T> targetClass) {
    return BeanHelper.beanToBean(this, targetClass);
  }
}
