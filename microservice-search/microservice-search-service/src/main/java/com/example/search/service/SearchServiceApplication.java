package com.example.search.service;

import com.qixinmini.common.security.annotation.EnableMicroserviceResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  @Description 检索服务启动类
 *  @author lijie
 *  @Date 2021/5/10 10:04
 */
@SpringBootApplication
@EnableFeignClients
@EnableMicroserviceResourceServer
public class SearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);
    }

}
