package com.qixinmini.common.security.configure;

import com.google.common.net.HttpHeaders;
import com.qixinmini.common.core.constant.MicroserviceConstant;
import com.qixinmini.common.core.utils.MicroserviceUtil;
import com.qixinmini.common.security.handler.MicroserviceAccessDeniedHandler;
import com.qixinmini.common.security.handler.MicroserviceAuthExceptionEntryPoint;
import com.qixinmini.common.security.properties.MicroserviceCloudSecurityProperties;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Base64Utils;

/**
 *  @Description  oauth 相关配置 利用自动装配实现
 *  @author lijie
 *  @Date 2020/11/12 16:44
 */
@Configuration
public class MicroserviceSecurityAutoConfigure {

    @Bean
    @ConditionalOnMissingBean(name = "securityProperties")
    public MicroserviceCloudSecurityProperties securityProperties(){
        return new MicroserviceCloudSecurityProperties();
    }

    /**
     * @Description 注入 security加密类
     * @author lijie
     * @createTime 2020-05-12 16:58:52
     * @param
     * @return org.springframework.security.crypto.password.PasswordEncoder
     */
    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public MicroserviceAccessDeniedHandler accessDeniedHandler(){
        return new MicroserviceAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authExceptionEntryPoint")
    public MicroserviceAuthExceptionEntryPoint authExceptionEntryPoint(){
        return new MicroserviceAuthExceptionEntryPoint();
    }


    @Bean
    @ConditionalOnMissingBean(name = "microserviceCloudSecurityProperties")
    public  MicroserviceCloudSecurityProperties microserviceCloudSecurityProperties(){
        return new MicroserviceCloudSecurityProperties();
    }

    /**
     * @Description feign 请求转发不会携带token  手动加入token
     * @author lijie
     * @createTime 2020-11-13 18:57:32
     * @param
     * @return
     */
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            // 添加 gateway Token
            String gatewayToken = new String(Base64Utils.encode(MicroserviceConstant.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(MicroserviceConstant.GATEWAY_TOKEN_HEADER, gatewayToken);
            // 得到当前的access_token 放入请求头
            String authorizationToken = MicroserviceUtil.getCurrentTokenValue();
            requestTemplate.header(HttpHeaders.AUTHORIZATION, MicroserviceConstant.OAUTH2_TOKEN_TYPE + authorizationToken);
        };
    }

    /**
     * @Description 注入微服务防护
     * @author lijie
     * @createTime 2020-11-19 16:42:07
     * @param
     * @return com.qixinmini.common.security.configure.MicroserviceProtectConfigure
     */
    @Bean
    @ConditionalOnProperty(name = "rainbow.cloud.security.enable",havingValue = "true",matchIfMissing = true)
    public MicroserviceProtectConfigure protectConfigure(){
        return new MicroserviceProtectConfigure();
    }
}
