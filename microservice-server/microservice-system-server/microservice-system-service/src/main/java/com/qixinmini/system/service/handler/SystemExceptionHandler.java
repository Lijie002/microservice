package com.qixinmini.system.service.handler;

import com.qixinmini.common.core.handler.BaseExceptionHandler;
import com.qixinmini.system.service.exception.SystemException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  @Description 认证模块异常处理
 *  @author lijie
 *  @Date 2020/5/13 18:24
 */
@RestControllerAdvice
public class SystemExceptionHandler extends BaseExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public ResponseEntity handlerAuthExceptionHandler(SystemException e){
        return ResponseEntity.ok(e.getMessage());
    }
}
