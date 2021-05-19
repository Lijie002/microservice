package com.qixinmini.system.service;

import com.qixinmini.common.security.annotation.EnableMicroserviceResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableFeignClients("com.qixinmini.system.api.feign")
@SpringBootApplication
@EnableMicroserviceResourceServer
@EnableAsync
@MapperScan("com.qixinmini.system.service.mapper")
public class SystemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemServiceApplication.class, args);
    }

}
