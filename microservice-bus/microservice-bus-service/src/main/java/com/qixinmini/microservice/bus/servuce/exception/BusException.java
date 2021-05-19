package com.qixinmini.microservice.bus.servuce.exception;
/**
 *  @Description 消息服务自定义异常
 *  @author lijie
 *  @Date 2020/5/25 15:30
 */
public class BusException extends RuntimeException{
    public BusException(String message){
        super(message);
    }
}
