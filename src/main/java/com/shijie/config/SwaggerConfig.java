package com.shijie.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment){
        Profiles profiles = Profiles.of("dev","test");//设置要显示的环境
        boolean Enable_Swagger_Flag=environment.acceptsProfiles(profiles);//返回是否在此环境中

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.shijie.controller")).build()
                .enable(Enable_Swagger_Flag);//是否打开Swagger
    }

    private ApiInfo apiInfo(){
        Contact contact=new Contact("严世杰","","yanshijie.work@aliyun.com");
        return new ApiInfo("API接口文档"
                , "yanshijie.work@aliyun.com"
                , "1.0"
                , "urn:tos"
                , contact
                , "Apache 2.0"
                , "http://www.apache.org/licenses/LICENSE-2.0"
                , new ArrayList()
        );

    }
}