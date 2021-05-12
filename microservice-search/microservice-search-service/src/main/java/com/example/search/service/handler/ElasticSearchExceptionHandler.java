package com.example.search.service.handler;

import com.example.search.service.exception.ElasticSearchException;
import com.qixinmini.common.core.handler.BaseExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  @Description 异常处理
 *  @author lijie
 *  @Date 2020/5/10 18:10
 */
@RestControllerAdvice
public class ElasticSearchExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(ElasticSearchException.class)
    public ResponseEntity elasticSearchExceptionHandler(ElasticSearchException e){
        return ResponseEntity.ok(e.getMessage());
    }
}
