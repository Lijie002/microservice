package com.qixinmini.oauth.handler;

import com.qixinmini.common.core.handler.BaseExceptionHandler;
import com.qixinmini.oauth.exception.AuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 *  @Description 认证模块异常处理
 *  @author lijie
 *  @Date 2021/5/12 18:24
 */
@RestControllerAdvice
public class AuthExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity handlerAuthExceptionHandler(AuthException e){
        return ResponseEntity.ok(e.getMessage());
    }
}
