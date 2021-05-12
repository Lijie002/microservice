package com.qixinmini.system.api.feign;

import com.qixinmini.common.core.model.TableData;
import com.qixinmini.system.api.dto.ArticleRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  @Description 文章feign
 *  @author lijie
 *  @Date 2020/5/11 15:54
 */
@FeignClient(name = "rainbow-search")
@RequestMapping("article")
public interface ArticleFeign {

     @PostMapping("pageList")
     TableData page(@RequestBody ArticleRequest articleRequest);

}
