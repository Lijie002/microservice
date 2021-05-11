package com.qixinmini.common.security.handler;

import com.qixinmini.common.core.constant.MicroserviceConstant;
import com.qixinmini.common.core.model.MicroserviceResponse;
import com.qixinmini.common.core.utils.MicrosericeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  @Description 处理403 没有权限异
 *  @author lijie
 *  @Date 2020/11/12 17:05
 */
@Slf4j
public class MicroserviceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String requestUri = request.getRequestURI();
        int status = HttpServletResponse.SC_UNAUTHORIZED;
        String message = "访问令牌不合法";
        log.error("客户端访问{}请求失败: {}", requestUri, message, authException);
        MicrosericeUtil.makeJsonResponse(response, status, new MicroserviceResponse().message(message));
    }
}
