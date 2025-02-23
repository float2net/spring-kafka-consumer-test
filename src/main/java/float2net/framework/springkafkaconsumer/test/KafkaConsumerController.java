package float2net.framework.springkafkaconsumer.test;

import float2net.framework.springkafkaconsumer.AbstractMessageProcessTask;
import float2net.framework.springkafkaconsumer.StringKafkaConsumerService;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@RestController
@Slf4j
public class KafkaConsumerController {

    @Resource
    private StringKafkaConsumerService consumerService;

    /**
     * 首页跳转到swagger-ui
     */
    @GetMapping("/")
    @ApiOperation(value = "", hidden = true)
    @SneakyThrows
    public void index(HttpServletResponse response) {
        response.sendRedirect("swagger-ui.html");
    }

    @GetMapping("/tasks")
    @ApiOperation(value = "列出所有已注册的任务")
    public Collection<AbstractMessageProcessTask<String, String>> findAllTasks() {
        return consumerService.findAllTasks();
    }

    /* FIXME: pause/resume 未见效果 */
//    @PostMapping("/task/pause")
//    @ApiOperation(value = "暂停某个任务的消费")
//    public boolean pauseTask(@RequestParam(value = "taskId") String taskId) {
//        return consumerService.pauseTask(taskId);
//    }
//
//    @PostMapping("/task/resume")
//    @ApiOperation(value = "继续某个任务的消费")
//    public boolean resumeTask(@RequestParam(value = "taskId") String taskId) {
//        return consumerService.resumeTask(taskId);
//    }

    @PostMapping("/task/start")
    @ApiOperation(value = "启动某个任务的消费")
    public boolean startTask(@RequestParam(value = "taskId") String taskId) {
        return consumerService.startTask(taskId);
    }

    @PostMapping("/task/stop")
    @ApiOperation(value = "停止某个任务的消费")
    public boolean stopTask(@RequestParam(value = "taskId") String taskId) {
        return consumerService.stopTask(taskId);
    }

}
