import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 五月的仓颉http://www.cnblogs.com/xrq730/p/7056614.html
 */
public class AbstractQueuedSynchronizerTest {
    @Test
    public void testAbstractQueuedSynchronizer() {
        Lock lock = new ReentrantLock();
        Runnable runnable0 = new ReentrantLockThread(lock);
        Thread thread0 = new Thread(runnable0);
        thread0.setName("线程0");
        Runnable runnable1 = new ReentrantLockThread(lock);
        Thread thread1 = new Thread(runnable1);
        thread1.setName("线程1");
        Runnable runnable2 = new ReentrantLockThread(lock);
        Thread thread2 = new Thread(runnable2);
        thread2.setName("线程2");
        thread1.start();
        thread0.start();
        thread2.start();
        for (; ; ) ;
    }

    private class ReentrantLockThread implements Runnable {
        private Lock lock;

        public ReentrantLockThread(Lock lock) {
            this.lock = lock;
        }

        public void run() {
            try {
                lock.lock();
                for (; ; ) ;
            } finally {
                lock.unlock();
            }
        }
    }
}

/**
 * @author 五月的仓颉http://www.cnblogs.com/xrq730/p/7067904.html
 */
class ConditionThread implements Runnable {
    private Lock lock;
    private Condition condition;

    public ConditionThread(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    public void run() {
        if ("线程0".equals(Thread.currentThread().getName())) {
            thread0Process();
        } else if ("线程1".equals(Thread.currentThread().getName())) {
            thread1Process();
        } else if ("线程2".equals(Thread.currentThread().getName())) {
            thread2Process();
        }
    }

    private void thread0Process() {
        try {
            lock.lock();
            System.out.println("线程0休息5秒");
            Thread.sleep(5000);
            condition.signal();
            System.out.println("线程0唤醒等待线程");
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }
    }

    private void thread1Process() {
        try {
            lock.lock();
            System.out.println("线程1阻塞");
            condition.await();
            System.out.println("线程1被唤醒");
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }
    }

    private void thread2Process() {
        try {
            System.out.println("线程2想要获取锁");
            lock.lock();
            System.out.println("线程2获取锁成功");
        } finally {
            lock.unlock();
        }
    }
}