package com.qixinmini.system.service.runner;

import com.qixinmini.common.core.utils.MicroserviceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 *  @Description 自定义启动日志
 *  @author lijie
 *  @Date 2020/5/26 13:48
 */
@Configuration
@RequiredArgsConstructor
public class runner implements ApplicationRunner {

    private final ConfigurableApplicationContext context;
    private final Environment environment;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (context.isActive()) {
            MicroserviceUtil.printSystemUpBanner(environment);
        }
    }
}
