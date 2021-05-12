package com.example.search.service.service;


import com.qixinmini.common.core.model.TableData;
import com.qixinmini.search.api.ArticleRequest;

/**
*  @Description articleEs 业务层接口
*  @author lijie
*  @Date 2021-5-10 10:58:59
*/
public interface ArticleEsService {

    /**
     * @Description Es 分页查询文章
     * @author lijie
     * @createTime 2020-05-10 17:38:53
     * @param articleRequest
     * @return
     */
    TableData page(ArticleRequest articleRequest);
}
