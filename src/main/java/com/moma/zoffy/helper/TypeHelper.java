package com.moma.zoffy.helper;

import java.util.Objects;

/**
 * TypeHelper
 *
 * <p>For Type Convent
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/15/18 - 4:09 PM.
 */
public class TypeHelper {

  /**
   * @author Created by ivan on 3:32 PM 12/24/18.
   * <p>//Cast Object 2 String
   * @param object :
   * @return java.lang.String
   **/
  public static String castToString(Object object) {
    if (Objects.isNull(object)) {
      return null;
    } else {
      return object.toString();
    }
  }
}
