package com.qixinmini.oauth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qixinmini.common.core.model.system.OauthClientDetails;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *  @Description 客户端信息接口
 *  @author lijie
 *  @Date 2021/5/11 10:23
 */
@Repository
public interface OauthClientDetailsMapper extends BaseMapper<OauthClientDetails> {
    /**
     * @Description 客户端信息分页
     * @author lijie
     * @createTime 2020-05-11 10:33:14
     * @param page
     * @param oauthClientDetails
     */
    IPage<OauthClientDetails> selectOauthClientDetailsPage(@Param("page")Page<OauthClientDetails> page, @Param("oauthClientDetails")OauthClientDetails oauthClientDetails);
}
