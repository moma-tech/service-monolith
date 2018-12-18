package com.moma.zoffy.helper;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.moma.zoffy.constants.ApiConstants;
import com.moma.zoffy.constants.enumeration.ApiStatusCodeEnum;
import com.moma.zoffy.constants.enumeration.HttpStatusCodeEnum;
import com.moma.zoffy.response.dto.ApiStatusInfo;
import com.moma.zoffy.response.dto.FailedResponse;
import com.moma.zoffy.wapper.ResponseWrapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;

/**
 * ResponseHelper
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/14/18 - 5:48 PM.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseHelper {

  public static void response(
      HttpServletRequest request,
      HttpServletResponse response,
      ApiStatusCodeEnum apiStatusCodeEnum,
      Exception exception) {
    response(request, response, apiStatusCodeEnum.transform(), exception);
  }

  public static void response(
      HttpServletRequest request,
      HttpServletResponse response,
      HttpStatusCodeEnum httpStatusCodeEnum,
      Exception exception) {
    response(request, response, httpStatusCodeEnum.transform(), exception);
  }

  public static void response(
      HttpServletRequest request,
      HttpServletResponse response,
      HttpStatusCodeEnum httpStatusCodeEnum) {
    response(request, response, httpStatusCodeEnum.transform(), null);
  }

  public static void response(
      HttpServletRequest request, HttpServletResponse response, ApiStatusInfo httpStatusInfo) {
    response(request, response, httpStatusInfo, null);
  }

  public static void response(
      HttpServletRequest request,
      HttpServletResponse response,
      ApiStatusInfo httpStatusInfo,
      Exception exception) {
    response(
        request,
        new ResponseWrapper(response, httpStatusInfo),
        buildFailedResponse(request, exception, httpStatusInfo));
  }

  public static void response(HttpServletRequest request, ResponseWrapper response, Object result) {
    LogHelper.printRequestLogInfo(
        (String) request.getAttribute(ApiConstants.REQUEST_ID),
        request.getParameterMap(),
        RequestHelper.getRequestBody(request),
        (String) request.getAttribute(ApiConstants.REQUEST_URL),
        (String) request.getAttribute(ApiConstants.REQUEST_MEPPING),
        (String) request.getAttribute(ApiConstants.REQUEST_METHOD),
        result,
        (Long) request.getAttribute(ApiConstants.REQUEST_START_TIME),
        RequestHelper.getIpAddr(request),
        (String) request.getAttribute(ApiConstants.COMPANY_ID));

    response.writeJsonResponse(result);
  }

  public static FailedResponse buildFailedResponse(
      HttpServletRequest request, Exception exception, ApiStatusInfo httpStatusInfo) {
    FailedResponse.FailedResponseBuilder builder = FailedResponse.builder();
    if (Objects.nonNull(httpStatusInfo)) {
      builder.code(httpStatusInfo.getCode()).msg(httpStatusInfo.getMsg());
    }
    if (Objects.nonNull(exception)) {
      builder.errorMsg(formatException(exception));
    }
    if (Objects.nonNull(request)) {
      builder.info((String) request.getAttribute(ApiConstants.REQUEST_ID));
    }
    builder.time(LocalDateTime.now());
    return builder.build();
  }

  public static String formatException(Exception exception) {
    if (null == exception) {
      return null;
    } else if (exception instanceof MethodArgumentNotValidException) {
      StringBuilder builder = new StringBuilder("校验失败:");
      List<ObjectError> allErrors =
          ((MethodArgumentNotValidException) exception).getBindingResult().getAllErrors();
      allErrors
          .stream()
          .findFirst()
          .ifPresent(
              error -> {
                builder
                    .append(((FieldError) error).getField())
                    .append(" 字段规则为 ")
                    .append(error.getDefaultMessage());
              });
      return builder.toString();
    } else if (exception instanceof MissingServletRequestParameterException) {
      StringBuilder builder = new StringBuilder("参数字段");
      MissingServletRequestParameterException ex =
          (MissingServletRequestParameterException) exception;
      builder.append(ex.getParameterName());
      builder.append("校验不通过");
      return builder.toString();
    } else if (exception instanceof MissingPathVariableException) {
      StringBuilder builder = new StringBuilder("路径字段");
      MissingPathVariableException ex = (MissingPathVariableException) exception;
      builder.append(ex.getVariableName());
      builder.append("校验不通过");
      return builder.toString();
    } else if (exception instanceof ConstraintViolationException) {
      StringBuilder builder = new StringBuilder("方法.参数字段");
      ConstraintViolationException ex = (ConstraintViolationException) exception;
      Optional<ConstraintViolation<?>> first = ex.getConstraintViolations().stream().findFirst();
      if (first.isPresent()) {
        ConstraintViolation<?> constraintViolation = first.get();
        builder.append(constraintViolation.getPropertyPath().toString());
        builder.append("校验不通过");
      }
      return builder.toString();
    } else if (exception instanceof HttpMessageNotReadableException) {
      if (exception.getCause() instanceof MismatchedInputException) {
        StringBuilder builder = new StringBuilder("参数字段");
        MismatchedInputException ex = ((MismatchedInputException) exception.getCause());
        List<Reference> referenceList = ex.getPath();
        referenceList
            .stream()
            .findFirst()
            .ifPresent(
                reference -> {
                  builder.append(reference.getFieldName());
                });
        builder.append(" 期望类型: ").append(ex.getTargetType());
        return builder.toString();
      }
    }
    return exception.toString();
  }
}
