package com.qixinmini.oauth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qixinmini.common.redis.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 *  @Description 客户端配置核心类
 *  @author lijie
 *  @Date 2021/5/11 18:38
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RedisClientDetailsService extends JdbcClientDetailsService {
    @Autowired
    @SuppressWarnings("all")
    private RedisService redisService;

    /**缓存 client的 redis key，这里是 hash结构存储 */
    private static final String CACHE_CLIENT_KEY = "client_details";


    @SuppressWarnings("all")
    public RedisClientDetailsService(DataSource dataSource, RedisService redisService) {
        super(dataSource);
        this.redisService = redisService;
    }

    /**
     * @Description 加载客户端信息
     * @author liuhu
     * @createTime
     * @param clientId 客户端ID
     * @return org.springframework.security.oauth2.provider.ClientDetails
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        ClientDetails clientDetails = null;
        // 先从redis里面拿  拿不到去mysql里面拿 然后放入redis
        String value = (String) redisService.hget(CACHE_CLIENT_KEY,clientId);
        if(null != value){
            clientDetails = JSONObject.parseObject(value, BaseClientDetails.class);
        }else{
            clientDetails = getAndCache(clientId);
        }
        return clientDetails;
    }

    /**
     * @Description 从数据库获得存入 redis 默认会查找表oauth_client_details
     * @author liuhu
     * @createTime 2020-05-13 09:49:06
     * @param clientId
     * @return org.springframework.security.oauth2.provider.ClientDetails
     */
    public ClientDetails getAndCache(String clientId) {
      ClientDetails  clientDetails = super.loadClientByClientId(clientId);
      if(null != clientDetails){
          redisService.hset(CACHE_CLIENT_KEY,clientId,JSONObject.toJSONString(clientDetails));
      }
      return clientDetails;
    }

    /**
     * @Description 清除客户端缓存
     * @author liuhu
     * @createTime 2020-06-12 10:58:52
     * @param clientId
     * @return void
     */
    public void deleteClientCache(String clientId){
        if(StringUtils.isNotBlank(clientId)){
            redisService.hdet(CACHE_CLIENT_KEY,clientId);
        }
    }
}
