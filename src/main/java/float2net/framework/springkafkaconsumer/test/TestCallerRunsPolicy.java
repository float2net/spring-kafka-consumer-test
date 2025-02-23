package float2net.framework.springkafkaconsumer.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestCallerRunsPolicy {

    public static void main(String[] args) {

        final ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("mytest-%d")
                .setDaemon(false).setPriority(Thread.NORM_PRIORITY).build();

        final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 4,
                1, TimeUnit.NANOSECONDS,
                new SynchronousQueue<>(), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

        for (int n = 1; n <= 10; n++) {
            log.info("loop " + n);
            poolExecutor.execute(() -> {
                log.info("start");
                try {
                    Thread.sleep(120 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("stop");
            });
        }

    }
}

/* 线程池使用说明参考： https://www.baeldung.com/java-rejectedexecutionhandler */
