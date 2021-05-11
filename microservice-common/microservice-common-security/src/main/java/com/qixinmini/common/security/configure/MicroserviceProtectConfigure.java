package com.qixinmini.common.security.configure;

import com.qixinmini.common.security.intercept.MicroserviceProtectIntercept;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  @Description 注册拦截器
 *  @author lijie
 *  @Date 2020/5/19 16:42
 */
public class MicroserviceProtectConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MicroserviceProtectIntercept());
    }
}
