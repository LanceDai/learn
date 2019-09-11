package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author LanceDai
 * @date 2019/3/22 14:37
 * @description *
 */
public class ScheduledExecutorServiceTest {
    private static ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1);
    public static void scheduledTask(int time, TimeUnit timeUnit, Runnable task) {
        System.out.println("submit time = " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        scheduledExecutorService.schedule(task, time, timeUnit);
    }

    public static void main(String[] args) {
        Runnable runnable = () -> {
            // task to run goes here
            System.out.println("execution time = " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
            System.out.println("Hello !!");
        };
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        scheduledTask(10, TimeUnit.SECONDS, runnable);
        scheduledExecutorService.shutdown();
        while (!scheduledExecutorService.isTerminated()){
        }
        System.out.println("is close");
    }
}