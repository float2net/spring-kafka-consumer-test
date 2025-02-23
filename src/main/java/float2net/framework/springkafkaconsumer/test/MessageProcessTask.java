package float2net.framework.springkafkaconsumer.test;

import float2net.framework.springkafkaconsumer.AbstractMessageProcessTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Random;

/**
 * 这是一个用于示例的<b>消息处理任务</b>类
 */
@Slf4j
public class MessageProcessTask extends AbstractMessageProcessTask<String, String> {

    public MessageProcessTask(String taskId, Config config) {
        super(taskId, config);
    }

    /**
     * 处理kafka消息的入口方法
     *
     * @param records kafka消息
     */
    @Override
    protected void process(ConsumerRecords<String, String> records) {
        /* 模拟一个耗时的消息处理过程 */
        while (true) {
            if (new Random().nextInt(30) == 3) {
                break;
            }
            try {
                log.trace("processEvent '{}' in progress ...", records);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
