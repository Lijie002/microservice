package com.qixinmini.microserviceupload.exception;
/**
 *  @Description 文件上传微服务异常
 *  @author lijie
 *  @Date 2020/5/26 15:27
 */
public class UploadException extends RuntimeException{
    public UploadException(String message){
         super(message);
    }
}
