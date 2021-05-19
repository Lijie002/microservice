package com.qixinmini.microservice.generator.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *  @Description Swagger 配置类
 *  @author lijie
 *  @Date 2021/5/18 14:06
 */
@Configuration
@EnableSwagger2
public class GeneratorSwaggerConfigure {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rainbow.generator.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("视频音频物流仓库")
                        .description("接口文档")
                        .version("1.0")
                        .contact(new Contact("qixinmini","https://github.com/Lijie002/microservice","18868392904@163.com"))
                        .license("Apache License2.0")
                        .licenseUrl("https://github.com/Lijie002/microservice")
                        .build());
    }
}
