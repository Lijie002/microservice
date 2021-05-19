package com.qixinmini.microservice.bus.servuce.handler;

import com.qixinmini.common.core.handler.BaseExceptionHandler;
import com.qixinmini.microservice.bus.servuce.exception.BusException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  @Description 消息服务异常处理
 *  @author lijie
 *  @Date 2020/5/25 15:31
 */
@RestControllerAdvice
public class BusExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(BusException.class)
    public ResponseEntity handlerBusException(BusException e){
        return ResponseEntity.ok(e.getMessage());
    }
}
