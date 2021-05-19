package com.qixinmini.microservice.generator.controller;

import com.qixinmini.microservice.generator.model.GeneratorConfig;
import com.qixinmini.microservice.generator.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 *  @Description 代码生成器控制层
 *  @author lijie
 *  @Date 2020/6/8 16:29
 */
@RequestMapping("generator")
@RestController
@RequiredArgsConstructor
@Api(tags = "代码生成")
public class GeneratorController {

    private final GeneratorService generatorService;

    /**
     * @Description
     * @author lijie
     * @createTime 2020-06-09 11:52:31
     * @param generatorConfig
     * @param response
     * @return void
     */
    @GetMapping
    @ApiOperation("代码生成")
    public void generate(GeneratorConfig generatorConfig, HttpServletResponse response) throws Exception {
        generatorService.generate(generatorConfig,response);
    }


}
