package com.example.search.service.exception;

/**
 *  @Description 文件上传微服务异常
 *  @author lijie
 *  @Date 2021/5/10 15:27
 */
public class ElasticSearchException extends RuntimeException{
    public ElasticSearchException(String message){
         super(message);
    }
}
