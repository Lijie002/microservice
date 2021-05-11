package com.qixinmini.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *  @Description 网关属性
 *  @author lijie
 *  @Date 2020/5/18 15:29
 */
@ConfigurationProperties(prefix = "microservice.gateway")
@PropertySource("classpath:MicroserviceGateway.properties")
@Configuration
@Data
public class MicroserviceGatewayGatewayProperties {
    /**黑名单url*/
    private String forbidUrl;
}
