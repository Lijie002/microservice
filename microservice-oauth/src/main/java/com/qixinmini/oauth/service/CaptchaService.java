package com.qixinmini.oauth.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  @Description captcha 接口
 *  @author lijie
 *  @Date 2021/5/11 14:51
 */
public interface CaptchaService {
    /**
     * @Description 生成验证码
     * @author lijie
     * @createTime 2021-05-11 14:53:32
     * @param request
     * @param response
     * @return void
     */
     void create(HttpServletRequest request, HttpServletResponse response) throws IOException;

     /**
      * @Description  校验验证码
      * @author lijie
      * @createTime 2021-05-11 14:56:05
      * @param key 验证码的key
      * @param code 验证码值
      * @return boolean
      */
     void checkCaptcha(String key,String code);
}
