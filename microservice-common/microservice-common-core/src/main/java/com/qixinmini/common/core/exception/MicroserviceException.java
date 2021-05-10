package com.qixinmini.common.core.exception;

/**
 *  @Description 系统自定义异常
 *  @author lijie
 *  @Date 2021/3/13 16:33
 */
public class MicroserviceException extends RuntimeException {
    public MicroserviceException(String message){
        super(message);
    }
}
