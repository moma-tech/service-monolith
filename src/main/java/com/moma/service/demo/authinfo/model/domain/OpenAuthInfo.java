package com.moma.service.demo.authinfo.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moma.zoffy.model.domain.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * OpenAuthInfo Entity
 *
 * @version 1.0
 * @author Created by ivan on 2:23 PM 12/24/18.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("open_auth_info")
public class OpenAuthInfo extends BaseModel {

  public static final String ID = "ID";
  public static final String APP_ID = "APP_ID";
  public static final String APP_NAME = "APP_NAME";
  public static final String APP_KEY = "APP_KEY";
  public static final String SIGN_KEY = "SIGN_KEY";
  public static final String APP_URL = "APP_URL";
  public static final String APP_STATUS = "APP_STATUS";
  public static final String APP_REMARK = "APP_REMARK";
  public static final String APP_MODEL = "APP_MODEL";
  public static final String APP_TYPE = "APP_TYPE";
  private static final long serialVersionUID = 1L;

  @TableId("ID")
  private Long id;
  /** 公司companyID */
  @TableField("APP_ID")
  private String appId;
  /** 公司名称 */
  @TableField("APP_NAME")
  private String appName;
  /** 生成唯一key */
  @TableField("APP_KEY")
  private String appKey;
  /** 生成签名key */
  @TableField("SIGN_KEY")
  private String signKey;
  /** 生成URL */
  @TableField("APP_URL")
  private String appUrl;
  /** 1：启用，2：禁用 */
  @TableField("APP_STATUS")
  private Boolean appStatus;

  @TableField("APP_REMARK")
  private String appRemark;
  /** 模块接入，1火车票，2飞机，3用车 */
  @TableField("APP_MODEL")
  private String appModel;
  /** 公司类型，如果是普通的公司则为0，如果是作为分销商或者分贝通作为供应商的公司则为1 */
  @TableField("APP_TYPE")
  private String appType;
}
