package com.qixinmini.system.service.controller;

import com.qixinmini.system.api.feign.ArticleFeign;
import com.qixinmini.system.api.model.Article;
import com.qixinmini.system.service.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
*  @Description article Controller
*  @author lijie
*  @Date 2021-5-1 10:58:59
*/
@RestController
@RequestMapping("article")
@RequiredArgsConstructor
@Api(tags = "文章接口")
public class ArticleController {

    private final ArticleService articleService;

    private final ArticleFeign articleFeign;

    /**
     * @Description 新增文章
     * @author lijie
     * @createTime 2021-05-1 17:28:43
     * @param article
     * @return org.springframework.http.ResponseEntity
     */
    @PostMapping
    @ApiOperation("新增文章")
    public ResponseEntity add(@RequestBody Article article){
       return ResponseEntity.ok(articleService.addArticle(article));
    }

    /**
     * @Description 新增文章
     * @author lijie
     * @createTime 2021-05-1 17:28:43
     * @param id
     * @return org.springframework.http.ResponseEntity
     */
    @GetMapping("{id}")
    @ApiOperation("新增文章")
    public ResponseEntity add(@PathVariable("id") Integer id){
        return ResponseEntity.ok(articleService.getById(id));
    }

    /**
     * @Description 查询文章分页
     * @author lijie
     * @createTime 2020-05-1 15:46:15
     * @param article
     * @return org.springframework.http.ResponseEntity
     */
    @PostMapping("page")
    @ApiOperation("查询文章分页")
    public ResponseEntity page(@RequestBody  Article article){
        return ResponseEntity.ok(articleService.page(article));
    }
}
