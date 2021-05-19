package com.qixinmini.microservice.bus.servuce;

import com.qixinmini.common.security.annotation.EnableMicroserviceResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  @Description 消息投递启动器
 *  @author lijie
 *  @Date 2020/5/22 17:43
 */
@EnableFeignClients
@SpringBootApplication
@EnableMicroserviceResourceServer
@MapperScan("com.qixinmini.microservice.bus.servuce.mapper")
public class BusApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusApplication.class, args);
    }

}
