package com.moma.service.demo.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.SpringBootConfiguration;
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
@SpringBootConfiguration
public class MybatisPlusConfiguration {

  /**
   * @author Created by ivan on 4:46 PM 12/21/18.
   *     <p>//TODO paginationInterceptor
   * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
  }

  /**
   * @author Created by ivan on 4:46 PM 12/21/18.
   *     <p>//TODO optimisticLockerInterceptor
   * @return com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor
   */
  @Bean
  public OptimisticLockerInterceptor optimisticLockerInterceptor() {
    return new OptimisticLockerInterceptor();
  }
}
