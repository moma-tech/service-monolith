package com.demo.api.demo.common.model.base;

import com.demo.framework.model.domain.SuperModel;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiModelProperty;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * BaseResponseList
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/27/18 - 2:42 PM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseResponseList<T> extends SuperModel {

  @ApiModelProperty(value = "内部记录ID")
  private String requestId;

  @ApiModelProperty(value = "响应代码")
  private Integer code;

  @ApiModelProperty(value = "响应类型")
  private String type;

  @ApiModelProperty(value = "响应信息")
  private String msg;

  @ApiModelProperty(value = "结果数据列表")
  private List<T> data;

  @SuppressWarnings("rawtypes")
  public static BaseResponseList fromJson(String json, Class clazz) {
    Gson gson =
        new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    Type objectType = type(BaseResponseList.class, clazz);
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
