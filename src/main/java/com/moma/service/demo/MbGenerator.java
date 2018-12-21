package com.moma.service.demo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.moma.zoffy.constants.SysConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MbGenerator
 *
 * <p>TODO
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/15/18 - 5:30 PM.
 */
@SuppressWarnings("AlibabaMethodTooLong")
public class MbGenerator {
  public static void main(String[] args) {
    MbGenerator.generator();
  }
  /** MySQL generator */
  public static void generator() {

    String tableName = "api_resource";
    String packageName = "resource";

    // 自定义需要填充的字段
    List<TableFill> tableFillList = new ArrayList<>();
    tableFillList.add(new TableFill("createTime", FieldFill.INSERT));
    tableFillList.add(new TableFill("updateTime", FieldFill.INSERT_UPDATE));

    // 代码生成器
    AutoGenerator mpg =
        new AutoGenerator()
            .setGlobalConfig(
                // 全局配置
                new GlobalConfig()
                    // 输出目录
                    .setOutputDir("/home/ivan/develop/code/")
                    // 是否覆盖文件
                    .setFileOverride(true)
                    // 开启 activeRecord 模式
                    .setActiveRecord(false)
                    // XML 二级缓存
                    .setEnableCache(false)
                    // XML ResultMap
                    .setBaseResultMap(false)
                    // XML columList
                    .setBaseColumnList(false)
                    // 是否生成 kotlin 代码
                    .setKotlin(false)
                    // 作者
                    .setAuthor("Ivan")
                    // 自定义文件命名，注意 %s 会自动填充表实体属性！
                    .setEntityName("%s")
                    .setMapperName("%sDao")
                    .setXmlName("%sMapper")
                    .setServiceName("%sService")
                    .setServiceImplName("%sServiceImpl")
                    .setControllerName("%sController"))
            .setDataSource(
                // 数据源配置
                new DataSourceConfig()
                    // 数据库类型
                    .setDbType(DbType.MYSQL)
                    .setTypeConvert(
                        new MySqlTypeConvert() {
                          @Override
                          public IColumnType processTypeConvert(
                              GlobalConfig globalConfig, String fieldType) {
                            if (fieldType.toLowerCase().contains("bit")) {
                              return DbColumnType.BOOLEAN;
                            }
                            if (fieldType.toLowerCase().contains("tinyint")) {
                              return DbColumnType.BOOLEAN;
                            }
                            if (fieldType.toLowerCase().contains("date")) {
                              return DbColumnType.LOCAL_DATE;
                            }
                            if (fieldType.toLowerCase().contains("time")) {
                              return DbColumnType.LOCAL_TIME;
                            }
                            if (fieldType.toLowerCase().contains("datetime")) {
                              return DbColumnType.LOCAL_DATE_TIME;
                            }
                            return super.processTypeConvert(globalConfig, fieldType);
                          }
                        })
                    .setDriverName("com.mysql.cj.jdbc.Driver")
                    .setUsername("root")
                    .setPassword("Ivanna83@")
                    .setUrl("jdbc:mysql://127.0.0.1:3306/open-api?characterEncoding=utf8"))
            .setStrategy(
                // 策略配置
                new StrategyConfig()
                    // 全局大写命名
                    .setCapitalMode(false)
                    // 去除前缀
                    .setTablePrefix("api_")
                    // 表名生成策略
                    .setNaming(NamingStrategy.underline_to_camel)
                    // 需要生成的表
                    .setInclude(new String[] {tableName})
                    // 自定义实体父类
                    .setSuperEntityClass("com.moma.zoffy.model.domain.BaseModel")
                    // 自定义实体，公共字段
                    // .setSuperEntityColumns("id")
                    .setTableFillList(tableFillList)
                    // 自定义 mapper 父类
                    .setSuperMapperClass("com.moma.zoffy.dao.BaseDao")
                    // 自定义 controller 父类
                    .setSuperControllerClass("com.moma.zoffy.controller.BaseController")
                    // 自定义 service 实现类父类
                    .setSuperServiceImplClass("com.moma.zoffy.service.impl.BaseServiceImpl")
                    // 自定义 service 接口父类
                    .setSuperServiceClass("com.moma.zoffy.service.BaseService")
                    // 【实体】是否生成字段常量（默认 false）
                    .setEntityColumnConstant(true)
                    // 【实体】是否为构建者模型（默认 false）
                    .setEntityBuilderModel(true)
                    // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                    .setEntityLombokModel(true)
                    // Boolean类型字段是否移除is前缀处理
                    .setEntityBooleanColumnRemoveIsPrefix(true)
                    .setRestControllerStyle(true)
                // .setControllerMappingHyphenStyle(true)
                )
            .setPackageInfo(
                // 包配置
                new PackageConfig()
                    .setParent("com.moma.service.demo." + packageName)
                    .setController("controller")
                    .setEntity("model.domain")
                    .setMapper("dao")
                    .setService("service")
                    .setServiceImpl("service.impl"))
            .setCfg(
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                new InjectionConfig() {
                  @Override
                  public void initMap() {
                    Map<String, Object> map = new HashMap<>(SysConstants.INIT_MAP_SIZE);
                    this.setMap(map);
                  }
                }.setFileOutConfigList(
                    Collections.<FileOutConfig>singletonList(
                        new FileOutConfig("/templates/mapper.xml.vm") {
                          // 自定义输出文件目录
                          @Override
                          public String outputFile(TableInfo tableInfo) {
                            return "/home/ivan/develop/code/xml/"
                                + tableInfo.getEntityName()
                                + ".xml";
                          }
                        })))
            .setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig().setXml(null)
                /* 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                .setController("...");
                .setEntity("...");
                .setMapper("...");
                .setXml("...");
                .setService("...");
                .setServiceImpl("...");*/
                );
    mpg.execute();
  }
}
