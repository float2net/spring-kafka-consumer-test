package float2net.framework.springkafkaconsumer.test;

import float2net.framework.springkafkaconsumer.AbstractMessageProcessTask.Config;
import float2net.framework.springkafkaconsumer.StringKafkaConsumerService;
import float2net.framework.springkafkaconsumer.StringMessageProcessTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用法示例
 */
@Slf4j
@Component
public class KafkaConsumerUsage implements InitializingBean {

    /**
     * 注入 spring-service
     */
    @Resource
    private StringKafkaConsumerService kafkaConsumerService;

    /**
     * 程序启动初始化后自动开始执行
     */
    @Override
    public void afterPropertiesSet() {

        /* 提交消费任务， 每个不同的kafka消费任务各自单独提交，比如下面有两个不同的kafka消费任务task-01和task-02 */

        //第一个消费任务task-01：
        //step 1: 定义配置参数（实际应用中，各项配置参数一般通过@Value来注入yml的配置）
//        final Config config01 = Config.builder().servers("10.30.26.154:9094").topics("wy1,wy2").groupId("wangyong").build();
        //step 2: 提交任务并立即启动消费
//        kafkaConsumerService.submitTask(new MessageProcessTask("task-01", config01), true);

        //第二个消费任务task-02：
        final Config config02 = Config.builder()
                .servers("localhost:9092")
                .topics("test-topic")
                .groupId("test-group")
                .consumerConcurrency(4)
                .processorConcurrency(8)
//                .maxPollIntervalMs(5 * 1000)
//                .maxPollRecords(1)
                .fetchMinBytes(1)
                .fetchMaxBytes(3)
                .build();
//        kafkaConsumerService.submitTask(new MessageProcessTask("task-02", config02), true);
        kafkaConsumerService.submitTask(new StringMessageProcessTask("task", config02) {
            @Override
            protected void process(ConsumerRecord<String, String> record) {
                //这里面也可以使用spring注入的自己的service bean.
                /* 模拟一个耗时的消息处理过程 */
                log.trace("开始处理消息 '{}' ...", record.value());
                long startTime = System.currentTimeMillis();
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.trace("结束处理消息 '{}' 耗时{}毫秒.", record.value(), System.currentTimeMillis() - startTime);
            }
        }, true);

        //或者，提交任务但在是暂时不启动消费
//        kafkaConsumerService.submitTask(new MessageProcessTask("task-01", config01));
        //后面在需要的时候，手动启动任务消费
//        kafkaConsumerService.startTask("task-001");
    }

}
