package com.moma.service.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MonolithBootstrap
 *
 * <p>Boot Strap Class
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/13/18 - 3:32 PM.
 */
@SpringBootApplication
@MapperScan({"com.moma.service.demo.*.dao"})
public class MonolithBootstrap {
  public static void main(String[] args) {
    SpringApplication.run(MonolithBootstrap.class, args);
  }
}
