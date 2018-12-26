package com.moma.service.demo.model.base;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moma.zoffy.model.domain.SuperModel;
import io.swagger.annotations.ApiModelProperty;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * BaseResponse
 *
 * <p>Base Response
 *
 * <p>Use gson to Covert Camel Case to Underscore
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/26/18 - 5:51 PM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseResponse<T> extends SuperModel {

  @ApiModelProperty(value = "内部记录ID", required = false)
  private String requestId;

  @ApiModelProperty(value = "响应代码", required = false)
  private String code;

  @ApiModelProperty(value = "响应信息", required = false)
  private String msg;

  @ApiModelProperty(value = "结果数据", required = false)
  private T data;

  @ApiModelProperty(value = "响应类型", required = false)
  private String type;

  public static BaseResponse fromCamelJson(String json, Class clazz) {
    Gson gson = new Gson();
    Type objectType = type(BaseResponse.class, clazz);
    return gson.fromJson(json, objectType);
  }

  public static BaseResponse fromUnderscoreJson(String json, Class clazz) {
    Gson gson =
        new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    Type objectType = type(BaseResponse.class, clazz);
    return gson.fromJson(json, objectType);
  }

  @SuppressWarnings("rawtypes")
  static ParameterizedType type(final Class raw, final Type... args) {
    return new ParameterizedType() {
      @Override
      public Type getRawType() {
        return raw;
      }

      @Override
      public Type[] getActualTypeArguments() {
        return args;
      }

      @Override
      public Type getOwnerType() {
        return null;
      }
    };
  }
}
