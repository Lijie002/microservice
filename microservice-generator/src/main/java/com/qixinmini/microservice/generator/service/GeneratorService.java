package com.qixinmini.microservice.generator.service;

import com.qixinmini.microservice.generator.model.GeneratorConfig;

import javax.servlet.http.HttpServletResponse;

/**
 *  @Description 代码生成器业务层接口
 *  @author lijie
 *  @Date 2020/6/8 17:06
 */
public interface GeneratorService {
    /**
     * @Description 代码生成
     * @author lijie
     * @createTime 2020-06-08 17:08:32
     * @param generatorConfig 表名
     * @param response
     * @return void
     */
    void generate(GeneratorConfig generatorConfig, HttpServletResponse response)throws Exception;
}
