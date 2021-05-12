package com.qixinmini.system.service.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

/**
 *  @Description 用户操作日志表
 *  @author lijie
 *  @Date 2020-06-04 11:10:55
 */
public interface LogService {

    /**
     * @Description 异步保存日志
     * @author lijie
     * @createTime 2020-06-04 11:59:47
     * @param joinPoint
     * @return void
     */
    @Async
    void saveLog(ProceedingJoinPoint joinPoint,String username,String ip);
}
