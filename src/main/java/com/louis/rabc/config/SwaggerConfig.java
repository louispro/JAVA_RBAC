package com.louis.rabc.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger配置
 *
 * @author louis
 * @date 2022/09/17
 */
@Configuration
//注解启动swagger的使用，同时在配置类中对swagger的通用参数进行配置
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket controllerApi(){
        //swagger设置，基本信息，要解析的接口路径及路径等
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                //设置通过什么方式定位需要自动生成文档的接口，这里通过定位方法上的@ApiOperation注解
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //接口URI路径设置，any是全路径，也可以通过pathSelector.regex()正则匹配
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 生成接口信息，包括标题，联系人等
     *
     * @return {@link ApiInfo}
     * @author zhangenguang
     * @date 2021/9/26 16:03
     */
    private ApiInfo  apiInfo(){
        return new ApiInfoBuilder()
                .title("RBAC权限管理接口文档")
                .description("RBAC权限管理")
                .contact(new Contact("louis_lai","http://github.com/louispro","1598358615@qq.com"))
                .version("版本号：1.0")
                .build();
    }


}
