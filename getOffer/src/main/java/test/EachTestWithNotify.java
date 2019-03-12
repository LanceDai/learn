package test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LanceDai
 * @date 2019/3/12 19:37
 * @description *
 */
public class EachTestWithNotify {
    private AtomicInteger n = new AtomicInteger(1);


    class NotifyWaitRunnable implements Runnable {
        NotifyWaitRunnable pre;

        @Override
        public void run() {
            while (n.get() < 100) {
                synchronized (pre) {
                    synchronized (this) {
                        this.notify();
                        System.out.println(Thread.currentThread().getName() + " : " + n.getAndIncrement());
                    }
                    try {
                        pre.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void setNotifyWaitRunnable(NotifyWaitRunnable pre) {
            this.pre = pre;
        }

    }

    public static void main(String[] args) {
        EachTestWithNotify test = new EachTestWithNotify();
        NotifyWaitRunnable a = test.new NotifyWaitRunnable();
        NotifyWaitRunnable b = test.new NotifyWaitRunnable();
        NotifyWaitRunnable c = test.new NotifyWaitRunnable();
        a.setNotifyWaitRunnable(c);
        b.setNotifyWaitRunnable(a);
        c.setNotifyWaitRunnable(b);
        new Thread(a).start();
        new Thread(b).start();
        new Thread(c).start();
    }
}
