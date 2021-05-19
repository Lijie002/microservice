package com.qixinmini.common.core.handler;

import com.qixinmini.common.core.exception.MicroserviceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *  @Description 公用异常处理 子类需要继承
 *  @author lijie
 *  @Date 2020/5/12 16:35
 */
@Slf4j
public class BaseExceptionHandler {

    /**
     * @Description 通用异常处理
     * @author lijie
     * @createTime 2020-05-12 16:40:37
     * @param e
     * @return org.springframework.http.ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerException(Exception e){
        log.error("系统内部异常",e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("系统内部异常");
    }

    /**
     * @Description 自定义异常处理
     * @author lijie
     * @createTime 2020-05-12 16:40:26
     * @param e
     * @return org.springframework.http.ResponseEntity
     */
    @ExceptionHandler(MicroserviceException.class)
    public ResponseEntity handlerException(MicroserviceException e){
        log.error("rainbow系统异常",e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}
