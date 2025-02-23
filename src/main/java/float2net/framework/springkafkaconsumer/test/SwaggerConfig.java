package float2net.framework.springkafkaconsumer.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {


    private final String BASE_PACKAGE = "float2net.framework.springkafkaconsumer";

    @Bean
    public Docket createDefaultDocket() {

        String desc = null;
        try {
            desc = new String(Files.readAllBytes(
                    Paths.get(SwaggerConfig.class.getResource("/api-spec.html").toURI())
            ), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //页面标题
                        .title("Float2net-Spring-Kafka消费任务api接口")
                        //创建人
                        .contact(new Contact("bigdata-team", "http://example.com", "float2net@gmail.com"))
                        //版本号
                        .version("1.0")
                        //描述
                        .description(desc)
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

}
