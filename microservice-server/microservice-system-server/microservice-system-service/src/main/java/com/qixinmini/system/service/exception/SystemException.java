package com.qixinmini.system.service.exception;
/**
 *  @Description 系统微服务异常
 *  @author lijie
 *  @Date 2021/5/13 02:27
 */
public class SystemException extends RuntimeException{
    public SystemException(String message){
         super(message);
    }
}
