package com.luo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2//开启swagger2自动配置
public class SwaggerConfig {

    //分组1
    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("myTest").apiInfo(apiInfo()).select().paths(PathSelectors.ant("/book/**")).build();
    }

    //分组2
    @Bean
    public Docket docket2() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("all").apiInfo(apiInfo()).select().paths(PathSelectors.any()).build();
    }

    //    该swagger说明
    private ApiInfo apiInfo() {
        Contact contact = new Contact("随笔", "https://xxxxx", "xxxxx@qq.com");
        return new ApiInfo(
                "随笔", // 标题
                "Swagger的学习", // 描述
                "v1.0", // 版本
                "https://组织链接", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "https://许可连接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }

}
