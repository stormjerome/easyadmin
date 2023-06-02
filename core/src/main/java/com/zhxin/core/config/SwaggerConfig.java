package com.zhxin.core.config;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfig
 * @Description //Swagger配置类
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/28 0028 下午 5:22
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private EasyAdminConfig easyAdminConfig;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                //扫描所有有注解的api
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("EasyAdmin接口文档")
                .contact(new Contact(easyAdminConfig.getAuthor(),
                        easyAdminConfig.getBlog(),
                        easyAdminConfig.getEmail()))
                .version("1.0")
                .build();
    }

}
