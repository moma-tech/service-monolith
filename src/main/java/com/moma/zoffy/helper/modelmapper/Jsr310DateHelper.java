package com.moma.zoffy.helper.modelmapper;

import java.time.ZoneId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Jsr310DateHelper
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/15/18 - 7:17 PM.
 */
@Data
@AllArgsConstructor
@Builder
public class Jsr310DateHelper {
  private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
  private static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
  private static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

  @Builder.Default private String datePattern = DEFAULT_DATE_PATTERN;
  @Builder.Default private String dateTimePattern = DEFAULT_DATE_TIME_PATTERN;
  @Builder.Default private String timePattern = DEFAULT_TIME_PATTERN;
  @Builder.Default private ZoneId zoneId = ZoneId.systemDefault();
}
