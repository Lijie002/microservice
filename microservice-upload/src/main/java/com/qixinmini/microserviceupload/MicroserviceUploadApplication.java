package com.qixinmini.microserviceupload;

import com.qixinmini.common.security.annotation.EnableMicroserviceResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  @Description 文书上传服务启动器
 *  @author lijie
 *  @Date 2020/6/4 15:46
 */
@SpringBootApplication
@EnableFeignClients
@EnableMicroserviceResourceServer
@MapperScan("com.qixinmini.microserviceupload.mapper")
public class MicroserviceUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceUploadApplication.class, args);
    }

}
