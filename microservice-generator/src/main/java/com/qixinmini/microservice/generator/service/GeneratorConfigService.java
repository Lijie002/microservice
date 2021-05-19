package com.qixinmini.microservice.generator.service;


import com.qixinmini.microservice.generator.model.GeneratorConfig;

/**
 *  @Description 代码生成器配置业务层接口
 *  @author lijie
 *  @Date 2020/6/8 17:11
 */
public interface GeneratorConfigService {

    /**
     * @Description 查询配置
     * @author lijie
     * @createTime 2020-06-08 17:13:02
     * @param
     * @return com.rainbow.generator.entity.GeneratorConfig
     */
    GeneratorConfig findGeneratorConfig();

    /**
     * @Description 修改配置
     * @author lijie
     * @createTime 2020-06-08 17:13:18
     * @param generatorConfig
     * @return void
     */
    GeneratorConfig updateGeneratorConfig(GeneratorConfig generatorConfig);

}
