package com.qixinmini.common.security.handler;

import com.qixinmini.common.core.model.MicroserviceResponse;
import com.qixinmini.common.core.utils.MicroserviceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  @Description 处理认证 401 未认证异常
 *  @author lijie
 *  @Date 2020/11/12 17:04
 */
@Slf4j
public class MicroserviceAccessDeniedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String requestUri = request.getRequestURI();
        int status = HttpServletResponse.SC_UNAUTHORIZED;
        String message = "访问令牌不合法";
        log.error("客户端访问{}请求失败: {}", requestUri, message, authException);
        MicroserviceUtil.makeJsonResponse(response, status, new MicroserviceResponse().message(message));
    }
}
