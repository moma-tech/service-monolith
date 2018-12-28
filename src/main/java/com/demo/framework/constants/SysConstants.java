package com.demo.framework.constants;

/**
 * SysConstants
 *
 * <p>System Level Constants
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/13/18 - 4:12 PM.
 */
public interface SysConstants {

  /** MAP 初始化大小，默认16 建议：(float) expectedSize / 0.75F + 1.0F */
  int INIT_MAP_SIZE = 16;
  /** HTTP Header Auth */
  String AUTHORIZATION_HEADER = "Authorization";

  /** String Split Comma */
  String GLOBE_SPLIT_COMMA = ",";
  /** String Split Dot */
  String GLOBE_SPLIT_DOT = ".";
}
