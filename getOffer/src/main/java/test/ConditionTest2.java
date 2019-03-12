package test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LanceDai
 * @date 2019/3/12 19:37
 * @description *
 */
public class ConditionTest2 {
    private AtomicInteger n = new AtomicInteger(1);
    private ReentrantLock lock = new ReentrantLock();


    class ConditionRunnable implements Runnable {
        Condition conditionPre;
        Condition conditionNow;

        @Override
        public void run() {
            while (n.get() < 100) {
                lock.lock();
                try {
                    conditionNow.signal();
                    System.out.println(Thread.currentThread().getName() + " : " + n.getAndIncrement());
                    conditionPre.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
            lock.lock();
            try {
                conditionNow.signal();
            } finally {
                lock.unlock();
            }
        }

        public ConditionRunnable(Condition conditionPre, Condition conditionNow) {
            this.conditionPre = conditionPre;
            this.conditionNow = conditionNow;
        }

    }

    public static void main(String[] args) {
        ConditionTest2 condition = new ConditionTest2();
        Condition condition1 = condition.lock.newCondition();
        Condition condition2 = condition.lock.newCondition();
        Condition condition3 = condition.lock.newCondition();
        new Thread(condition.new ConditionRunnable(condition3, condition1)).start();
        new Thread(condition.new ConditionRunnable(condition1, condition2)).start();
        new Thread(condition.new ConditionRunnable(condition2, condition3)).start();
    }
}
