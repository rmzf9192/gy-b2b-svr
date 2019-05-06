package com.el.cfg;

import com.google.common.base.Predicate;
import lombok.val;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * <a href="https://springfox.github.io/springfox/">SpringFox Swagger Document</a>
 *
 * @author neo.pan
 * @since 2018/04/02
 */
@ConditionalOnProperty("swagger")
@Profile("dev")
@Configuration
@EnableSwagger2
public class B2bSwaggerConfig {

    private static Predicate<String> paths() {
        return or(Arrays.asList(
            regex("/api/.*"),
            regex("/edp/.*"),
            regex("/sec/.*"),
            regex("/dev/.*"),
            regex("/register/.*")));
    }

    @Bean
    public Docket api() {
        val xsrfParam = new ParameterBuilder()
            .name("el-xsrf").parameterType("header")
            .modelRef(new ModelRef("string"))
            .build();
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .paths(paths())
            .build()
            .globalOperationParameters(Collections.singletonList(xsrfParam));
    }

}
