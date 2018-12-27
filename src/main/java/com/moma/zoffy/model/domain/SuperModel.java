package com.moma.zoffy.model.domain;

import com.moma.zoffy.helper.JacksonHelper;
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
public class SuperModel {

  public <T> T beanToBean(Class<T> targetClass) {
    return BeanHelper.beanToBean(this, targetClass);
  }

  public String presentJsonData() {
    return JacksonHelper.toJson(this);
  }
}
