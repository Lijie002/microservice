package com.qixinmini.oauth.filter;

import com.qixinmini.common.core.constant.CaptchaConstant;
import com.qixinmini.common.core.constant.EndpointConstant;
import com.qixinmini.common.core.constant.GrantTypeConstant;
import com.qixinmini.common.core.constant.ParamsConstant;
import com.qixinmini.common.core.model.MicroserviceResponse;
import com.qixinmini.common.core.utils.MicroserviceUtil;
import com.qixinmini.oauth.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  @Description 验证码校验 OncePerRequestFilter表示只执行一次
 *  @author liuhu
 *  @Date 2020/5/13 14:49
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateCaptchaFilter extends OncePerRequestFilter {


    private final CaptchaService captchaService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        RequestMatcher matcher = new AntPathRequestMatcher(EndpointConstant.OAUTH_TOKEN, HttpMethod.POST.toString());
        // 如果改请求时请求token 登录的 则校验 执行 验证码
        if (matcher.matches(httpServletRequest)
                && StringUtils.equalsIgnoreCase(httpServletRequest.getParameter(ParamsConstant.GRANT_TYPE), GrantTypeConstant.PASSWORD)) {
            try {
                validateCode(httpServletRequest);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (Exception e) {
                MicroserviceResponse response = new MicroserviceResponse();
                MicroserviceUtil.makeFailureResponse(httpServletResponse, response.message(e.getMessage()));
                log.error(e.getMessage(), e);
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private void validateCode(HttpServletRequest httpServletRequest)  {
        String key = httpServletRequest.getParameter(CaptchaConstant.CAPTCHA_KEY);
        String code = httpServletRequest.getParameter(CaptchaConstant.CAPTCHA_VALUE);
        captchaService.checkCaptcha(key, code);
    }
}
