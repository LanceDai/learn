package test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Logger;

public class ForkJoinPoolTest extends RecursiveTask<Integer> {
    private static final Logger log = Logger.getLogger(ForkJoinPoolTest.class.getCanonicalName());

    public static final int threshold = 2;
    private int start;
    private int end;

    public ForkJoinPoolTest(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        int sum = 0;

        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            ForkJoinPoolTest leftTask = new ForkJoinPoolTest(start, middle);
            ForkJoinPoolTest rightTask = new ForkJoinPoolTest(middle + 1, end);

            // 执行子任务
//            leftTask.fork();
//            rightTask.fork();
            invokeAll(leftTask, rightTask);
            // 等待任务执行结束合并其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkjoinPool = new ForkJoinPool(100);

        //生成一个计算任务，计算1+2+3+4
        ForkJoinPoolTest task = new ForkJoinPoolTest(1, 100);

        //执行一个任务
        long start = System.currentTimeMillis();

        try {
            Integer result = forkjoinPool.invoke(task);
            log.info("result: " + result);
            log.info("cost: " + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            log.info("exception " + e);
        }
    }
}
