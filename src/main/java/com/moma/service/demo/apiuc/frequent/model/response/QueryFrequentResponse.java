package com.moma.service.demo.apiuc.frequent.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * QueryFrequentResponse
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/27/18 - 5:08 PM.
 */
@Data
public class QueryFrequentResponse {
  @ApiModelProperty(value = "应用场景", example = "11", required = false)
  private int category;

  @ApiModelProperty(value = "1 因公 2 因私", example = "1", required = false)
  private int useType;

  @ApiModelProperty(value = "联系人主键ID", example = "5bd7c6c5c92c5503c8c50203", required = false)
  private String id;

  @ApiModelProperty(value = "同行人在组织架构的id", example = "5940f11723445f523b932411", required = false)
  private String selectedEmployeeId;

  @ApiModelProperty(value = "登录用户ID", example = "5b07b3b723445f587fa7d548", required = false)
  private String ownerId;

  @ApiModelProperty(value = "如果是根据第三方联系人ID添加的话，就会返回", example = "222", required = false)
  private String thirdFrequentId;

  @ApiModelProperty(value = "同行人姓名", example = "肖健", required = false)
  private String name;

  @ApiModelProperty(value = "1：男2：女", example = "1", required = false)
  private int gender;

  @ApiModelProperty(value = "生日", example = "1986-10-31", required = false)
  private String birthDate;

  @ApiModelProperty(value = "邮件", example = "xiao.@qq.com", required = false)
  private String email;

  @ApiModelProperty(value = "联系电话", example = "13654611111", required = false)
  private String phoneNum;

  @ApiModelProperty(value = "是否来自于组织架构", example = "true", required = false)
  private boolean orgnazationFlag;

  @ApiModelProperty(value = "是否为员工", example = "true", required = false)
  private boolean employeeFlag;

  @ApiModelProperty(value = "姓", example = "xiao", required = false)
  private String familyName;

  @ApiModelProperty(value = "名", example = "jian", required = false)
  private String givenName;

  @ApiModelProperty(value = "证书有效期", example = "2020-10-10", required = false)
  private String certValidDate;

  @ApiModelProperty(value = "国籍编码", example = "US", required = false)
  private String nationality;

  @ApiModelProperty(value = "国籍名称", example = "美国", required = false)
  private String nationalityName;

  @ApiModelProperty(value = "1：国内 2：国际", example = "1", required = false)
  private int frequentType;

  @ApiModelProperty(value = "最后一次使用时间", example = "2018-10-10", required = false)
  private String lastUsed;

  @ApiModelProperty(value = "使用次数", example = "0", required = false)
  private int useNum;

  @ApiModelProperty(
      value = "1:身份证 2:护照 3:回乡证 4:台胞证 5:港澳通行证 6:大陆居民往来台湾通行证",
      example = "",
      required = false)
  private IdTypeEntityBean idTypeEntity;

  @ApiModelProperty(value = "", example = "2***1", required = false)
  private String idNumber;

  @ApiModelProperty(value = "", example = "运维部", required = false)
  private String unitName;

  @Data
  public static class IdTypeEntityBean {

    /** key : 2 value : 护照 */
    @ApiModelProperty(value = "", example = "2", required = false)
    private int key;

    @ApiModelProperty(value = "", example = "护照", required = false)
    private String value;
  }
}
