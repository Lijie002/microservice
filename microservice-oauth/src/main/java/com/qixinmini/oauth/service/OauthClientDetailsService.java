package com.qixinmini.oauth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qixinmini.common.core.model.QueryRequest;
import com.qixinmini.common.core.model.system.OauthClientDetails;

/**
 *  @Description 客户端信息
 *  @author lijie
 *  @Date 2021/05/12 10:05
 */
public interface OauthClientDetailsService{

    /**
     * @Description 查询（分页）
     * @author lijie
     * @createTime 2021-05-12 10:04:50
     * @param request
     * @param oauthClientDetails
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rainbow.common.core.entity.system.OauthClientDetails>
     */
    IPage<OauthClientDetails> findOauthClientDetails(QueryRequest request, OauthClientDetails oauthClientDetails);

    /**
     * @Description 根据主键查询
     * @author lijie
     * @createTime 2021-05-12 10:04:02
     * @param clientId
     * @return com.rainbow.common.core.entity.system.OauthClientDetails
     */
    OauthClientDetails findById(String clientId);

    /**
     * @Description 新增
     * @author lijie
     * @createTime 2021-05-12 10:04:14
     * @param oauthClientDetails
     * @return void
     */
    OauthClientDetails addOauthClientDetails(OauthClientDetails oauthClientDetails);

   /**
    * @Description 修改
    * @author lijie
    * @createTime 2021-05-12 10:04:24
    * @param oauthClientDetails
    * @return void
    */
    void updateOauthClientDetails(OauthClientDetails oauthClientDetails);

   /**
    * @Description 删除
    * @author lijie
    * @createTime 2021-05-12 10:04:36
    * @param clientIds
    * @return void
    */
    void deleteOauthClientDetails(String clientIds);

}
