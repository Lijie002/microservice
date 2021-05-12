package com.qixinmini.system.service.service.impl;


import com.qixinmini.common.core.model.TableData;
import com.qixinmini.system.api.dto.ArticleRequest;
import com.qixinmini.system.api.feign.ArticleFeign;
import com.qixinmini.system.api.model.Article;
import com.qixinmini.system.service.exception.SystemException;
import com.qixinmini.system.service.mapper.ArticleMapper;
import com.qixinmini.system.service.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
*  @Description article 业务层实现类
*  @author lijie
*  @Date 2020-6-10 10:58:59
*/
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    private final ArticleFeign articleFeign;

    @Override
    public Article addArticle(Article article) {
        try {
            articleMapper.insert(article);
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("新增文章失败");
        }
        return article;
    }

    @Override
    public Article getById(Integer id) {
        Article article = null;
        try {
            article = articleMapper.selectById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("根据ID查询失败");
        }
        return article;
    }

    @Override
    public TableData page(Article article) {
        TableData tableData = null;
        try {
            ArticleRequest articleRequest = new ArticleRequest();
            BeanUtils.copyProperties(article,articleRequest);
            if(null != article.getQueryRequest()){
                articleRequest.setCurrent(article.getQueryRequest().getPageNum());
                articleRequest.setSize(article.getQueryRequest().getPageSize());
            }
            tableData = articleFeign.page(articleRequest);
        }catch (Exception e){
                 e.printStackTrace();
                 throw new SystemException("查询分页失败");
        }
        return tableData;
    }

}
