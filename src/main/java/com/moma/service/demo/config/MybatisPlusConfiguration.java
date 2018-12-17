package com.moma.service.demo.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * MybatisPlusConfiguration
 *
 * <p>Mybatis Plus Configuration
 *
 * @see <a href="https://mp.baomidou.com/guide/">MyBatis-Plus</a>
 * @author ivan
 * @version 1.0 Created by ivan on 12/14/18 - 3:36 PM.
 */
// @SpringBootConfiguration
public class MybatisPlusConfiguration {

  /**
   * 分页
   *
   * @return
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
  }

  /**
   * 乐观锁
   *
   * @return
   */
  @Bean
  public OptimisticLockerInterceptor optimisticLockerInterceptor() {
    return new OptimisticLockerInterceptor();
  }
}
