package com.demo.api.demo.access.resource.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.demo.framework.model.domain.SuperModel;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Api Resource Entity
 *
 * @author Ivan
 * @since 2018-12-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("api_resource")
public class Resource extends SuperModel {

  public static final String ID = "id";
  public static final String API_NAME = "api_name";
  public static final String API_PATH = "api_path";
  public static final String API_METHOD = "api_method";
  public static final String API_TYPE = "api_type";
  public static final String API_GROUP = "api_group";
  public static final String API_VERSION = "api_version";
  public static final String API_AUTH = "api_auth";
  public static final String CREATE_TIME = "create_time";
  public static final String UPDATE_TIME = "update_time";
  private static final long serialVersionUID = 1L;
  /** 资源ID */
  private String id;
  /** 资源名称 */
  private String apiName;
  /** 资源路径 */
  private String apiPath;
  /** 请求方式 */
  private String apiMethod;
  /** 资源类型 */
  private Integer apiType;
  /** 资源分组 */
  private String apiGroup;
  /** 资源版本 */
  private String apiVersion;
  /** 资源鉴权标识 */
  private String apiAuth;
  /** 创建时间 */
  private LocalDate createTime;
  /** 最后更新时间 */
  private LocalDate updateTime;
}
