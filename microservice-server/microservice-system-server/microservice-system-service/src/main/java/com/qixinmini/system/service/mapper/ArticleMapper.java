package com.qixinmini.system.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qixinmini.system.api.model.Article;
import org.springframework.stereotype.Repository;

/**
*  @Description article 持久层接口
*  @author lijie
*  @Date 2020-6-10 10:58:59
*/
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

}
