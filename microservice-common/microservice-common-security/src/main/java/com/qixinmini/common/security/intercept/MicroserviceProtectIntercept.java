package com.qixinmini.common.security.intercept;

import com.qixinmini.common.core.constant.MicroserviceConstant;
import com.qixinmini.common.core.model.MicroserviceResponse;
import com.qixinmini.common.core.utils.MicroserviceUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MicroserviceProtectIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求头判断是否匹配网关token
        String token = request.getHeader(MicroserviceConstant.GATEWAY_TOKEN_HEADER);
        if(StringUtils.isBlank(token)){
            MicroserviceUtil.makeJsonResponse(response, HttpServletResponse.SC_FORBIDDEN, new MicroserviceResponse().message("请通过网关获取资源"));
            return  false;
        }else{
            String value = new String(Base64Utils.encode(MicroserviceConstant.GATEWAY_TOKEN_VALUE.getBytes()));
            if(StringUtils.equals(token,value)){
                return true;
            }
        }
        return false;
    }
}
