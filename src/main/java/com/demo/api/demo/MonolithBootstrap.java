package com.demo.api.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * MonolithBootstrap
 *
 * <p>Boot Strap Class
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/13/18 - 3:32 PM.
 */
@SpringBootApplication
@EnableWebMvc
@MapperScan({"com.demo.api.demo.**.dao"})
public class MonolithBootstrap {
  public static void main(String[] args) {
    SpringApplication.run(MonolithBootstrap.class, args);
  }
}
