package com.qixinmini.system.service.service;


import com.qixinmini.common.core.model.TableData;
import com.qixinmini.system.api.model.Article;

/**
*  @Description article 业务层接口
*  @author lijie
*  @Date 2020-6-10 10:58:59
*/
public interface ArticleService {


    /**
     * @Description 新增
     * @author lijie
     * @createTime 2020-06-10 13:28:33
     * @param article
     * @return com.rainbow.search.api.entity.Article
     */
    Article addArticle(Article article);

    /**
     * @Description 根据Id查询
     * @author lijie
     * @createTime 2020-06-10 17:29:57
     * @param id
     * @return com.rainbow.server.system.api.entity.Article
     */
    Article getById(Integer id);

    TableData page(Article article);
}
