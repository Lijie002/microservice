package com.qixinmini.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.qixinmini.common.core.constant.MicroserviceConstant;
import com.qixinmini.common.core.model.MicroserviceResponse;
import com.qixinmini.gateway.properties.MicroserviceGatewayGatewayProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class MicroserviceGatewayFilter implements GlobalFilter {

    private final MicroserviceGatewayGatewayProperties gatewayProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 禁止客户端的访问资源逻辑
        Mono<Void> checkForbidUriResult = checkForbidUrl(request, response);
        if (checkForbidUriResult != null) {
            return checkForbidUriResult;
        }
        printLog(exchange);
        // 加入验证标识 禁止请求绕过网关
        byte[] token = Base64Utils.encode((MicroserviceConstant.GATEWAY_TOKEN_VALUE).getBytes());
        ServerHttpRequest build = request.mutate().header(MicroserviceConstant.GATEWAY_TOKEN_HEADER, new String(token)).build();
        ServerWebExchange newExchange = exchange.mutate().request(build).build();
        return chain.filter(newExchange);
    }



    private void printLog(ServerWebExchange exchange) {
        URI url = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
        Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
        LinkedHashSet<URI> uris = exchange.getAttribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        URI originUri = null;
        if (uris != null) {
            originUri = uris.stream().findFirst().orElse(null);
        }
        if (url != null && route != null && originUri != null) {
            log.info("转发请求：{}://{}{} --> 目标服务：{}，目标地址：{}://{}{}，转发时间：{}",
                    originUri.getScheme(), originUri.getAuthority(), originUri.getPath(),
                    route.getId(), url.getScheme(), url.getAuthority(), url.getPath(), LocalDateTime.now()
            );
        }
    }

    /**
     * @Description  黑名单
     * @author lijie
     * @createTime 2020-05-18 16:52:06
     * @param request
     * @param response
     * @return reactor.core.publisher.Mono<java.lang.Void>
     */
   public Mono<Void>  checkForbidUrl(ServerHttpRequest request,ServerHttpResponse response){
       boolean forbid =false;
       String uri = request.getURI().toString();
       AntPathMatcher matcher = new AntPathMatcher();
       if(null !=  gatewayProperties.getForbidUrl()){
           String[] forbidRequestUris = gatewayProperties.getForbidUrl().split(",");
           if (forbidRequestUris != null && ArrayUtils.isNotEmpty(forbidRequestUris)) {
               for (String u : forbidRequestUris) {
                   if (matcher.match(u, uri)) {
                       forbid = true;
                   }
               }
           }
       }
       if (forbid) {
           MicroserviceResponse microserviceResponse = new MicroserviceResponse().message("该URI不允许外部访问");
           return makeResponse(response, microserviceResponse);
       }
       return null;
    }

    private Mono<Void> makeResponse(ServerHttpResponse response, MicroserviceResponse microserviceResponse) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(microserviceResponse).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
}
