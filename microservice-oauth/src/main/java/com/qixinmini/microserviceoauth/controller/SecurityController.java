package com.qixinmini.microserviceoauth.controller;

import com.rainbow.auth.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 *  @Description 认证提供的接口
 *  @author liuhu
 *  @Date 2020/5/13 13:23
 */
@RestController
@RequiredArgsConstructor
public class SecurityController {

    private final CaptchaService captchaService;

    /**
     * @Description 生成验证码
     * @author liuhu
     * @createTime 2020-05-13 14:41:41
     * @param request
     * @param response
     * @return void
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
          captchaService.create(request,response);
    }

    /**
     * @Description 认证服务器校验token
     * @author liuhu
     * @createTime 2020-05-19 18:54:54
     * @param principal
     * @return java.security.Principal
     */
    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

}
