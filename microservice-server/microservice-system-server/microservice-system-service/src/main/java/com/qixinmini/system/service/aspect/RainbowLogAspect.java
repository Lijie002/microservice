package com.qixinmini.system.service.aspect;

import com.qixinmini.common.core.utils.MicroserviceUtil;
import com.qixinmini.system.service.service.LogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 *  @Description aop增加操作日志
 *  @author lijie
 *  @Date 2021/5/4 14:30
 */
@Configuration
@Aspect
@RequiredArgsConstructor
public class RainbowLogAspect {

    private final LogService logService;

    @Pointcut("@annotation(com.qixinmini.system.service.annotation.RainbowLog)")
    public void pointcut(){

    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint point){

        try {
            point.proceed();
            String username = MicroserviceUtil.getCurrentUsername();
            String ip = MicroserviceUtil.getHttpServletRequestIpAddress();
            logService.saveLog(point, username, ip);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
