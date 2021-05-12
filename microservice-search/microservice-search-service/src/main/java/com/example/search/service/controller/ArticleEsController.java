package com.example.search.service.controller;

import com.example.search.service.service.ArticleEsService;
import com.qixinmini.common.core.model.TableData;
import com.qixinmini.search.api.ArticleRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*  @Description article Controller
*  @author lijie
*  @Date 2021-5-10 10:58:59
*/
@RestController
@RequestMapping("article")
@RequiredArgsConstructor
@Api(tags = "文章接口")
public class ArticleEsController {

    private final ArticleEsService articleEsService;

    @PostMapping("pageList")
    @ApiOperation("文章操作")
    public TableData page(@RequestBody ArticleRequest articleRequest){
       return articleEsService.page(articleRequest);
    }
}
