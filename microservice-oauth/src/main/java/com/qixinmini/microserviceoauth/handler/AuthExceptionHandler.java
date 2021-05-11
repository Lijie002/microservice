package com.qixinmini.microserviceoauth.handler;

import com.rainbow.auth.exception.AuthException;
import com.rainbow.common.core.handler.BaseExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 *  @Description 认证模块异常处理
 *  @author liuhu
 *  @Date 2020/5/13 18:24
 */
@RestControllerAdvice
public class AuthExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity handlerAuthExceptionHandler(AuthException e){
        return ResponseEntity.ok(e.getMessage());
    }
}
