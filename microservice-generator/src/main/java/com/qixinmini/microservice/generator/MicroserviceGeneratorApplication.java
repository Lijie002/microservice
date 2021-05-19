package com.qixinmini.microservice.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  @Description 代码生成器启动类
 *  @author lijie
 *  @Date 2020/6/8 17:54
 */
@SpringBootApplication
@MapperScan("com.qixinmini.microservice.generator.mapper")
public class MicroserviceGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceGeneratorApplication.class, args);
    }

}
