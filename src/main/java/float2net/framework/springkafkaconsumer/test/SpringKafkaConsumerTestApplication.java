package float2net.framework.springkafkaconsumer.test;

import float2net.framework.springkafkaconsumer.StringKafkaConsumerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans({
        @ComponentScan(basePackages = "float2net.framework.springkafkaconsumer"),
        @ComponentScan(basePackageClasses = StringKafkaConsumerService.class) //扫描KafkaConsumerService
})
public class SpringKafkaConsumerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaConsumerTestApplication.class, args);
    }

}
