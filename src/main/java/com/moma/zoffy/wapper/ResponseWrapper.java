package com.moma.zoffy.wapper;

import com.google.common.base.Throwables;
import com.moma.zoffy.helper.JacksonHelper;
import com.moma.zoffy.response.dto.ApiStatusInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MimeTypeUtils;

/**
 * ResponseWrapper
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/14/18 - 7:51 PM.
 */
@Slf4j
public class ResponseWrapper extends HttpServletResponseWrapper {
  private ApiStatusInfo apiStatusInfo;

  public ResponseWrapper(HttpServletResponse response) {
    super(response);
  }

  public ResponseWrapper(HttpServletResponse response, ApiStatusInfo apiStatusInfo) {
    super(response);
  }

  public void writeJsonResponse(Object obj) {
    if (super.isCommitted()) {
      log.warn("Response is commit");
    } else {
      super.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
      super.setCharacterEncoding(StandardCharsets.UTF_8.name());
      try (PrintWriter writer = super.getWriter()) {
        writer.print(JacksonHelper.toJson(obj));
        writer.flush();
      } catch (IOException e) {
        log.warn(
            "Error: Response printJson faild, stackTrace: {}", Throwables.getStackTraceAsString(e));
      }
    }
  }
}
