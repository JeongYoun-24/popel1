package com.springstudy.shop.config;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.lang.model.SourceVersion;
import java.util.Locale;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    @Bean
    public Doclet api(){
        return new Doclet(DocumentationType.OAS_30) {
            @Override
            public void init(Locale locale, Reporter reporter) {

            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public Set<? extends Option> getSupportedOptions() {
                return null;
            }

            @Override
            public SourceVersion getSupportedSourceVersion() {
                return null;
            }

            @Override
            public boolean run(DocletEnvironment environment) {
                return false;
            }
        }
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.springstudy.shop.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Boot 01 Project Swagger")
                .build();
    }


}
