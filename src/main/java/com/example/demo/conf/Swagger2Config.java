package com.example.demo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2020/7/28]
 * Profile 运行环境 dev-开发环境,test-测试环境,prod-正式环境 对应resources下application配置文件
 **/
@Profile({"!prod"})
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestfulApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> globalParameters = new ArrayList<>();
        ticketPar.name("Authentication")
                .description("用户登录授权信息")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        globalParameters.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.logic.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(globalParameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("demo 接口文档")
                .description("接口调试页面。✍")
                .version("1.0")
                .build();
    }


}
