package com.moma.zoffy.helper;

import java.util.Objects;

/**
 * TypeHelper
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/15/18 - 4:09 PM.
 */
public class TypeHelper {

  public static String castToString(Object object) {
    if (Objects.isNull(object)) {
      return null;
    } else {
      return object.toString();
    }
  }
}
