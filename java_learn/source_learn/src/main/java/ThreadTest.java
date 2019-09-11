import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class ThreadTest {

    static void test1() throws InterruptedException {
        Semaphore semaphoreA = new Semaphore(1);
        Semaphore semaphoreB = new Semaphore(0);
        Semaphore semaphoreC = new Semaphore(0);
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Thread threadC = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    semaphoreC.acquire();
                    System.out.println(Thread.currentThread().getName() + " --> C");
                    semaphoreA.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }, "Thread_C");
        Thread threadA = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    semaphoreA.acquire();
                    System.out.println(i + " ==> " + Thread.currentThread().getName() + " --> A");
                    semaphoreB.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }, "Thread_A");
        Thread threadB = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    semaphoreB.acquire();
                    System.out.println(Thread.currentThread().getName() + " --> B");
                    semaphoreC.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }, "Thread_B");

        threadA.start();
        threadB.start();
        threadC.start();
        countDownLatch.await();
        System.out.println("finish");
    }

    void test2() {
        // A-> pre C, self A
        // B-> pre A, self B
        // C-> pre B, self C
        class EchoThread implements Runnable {


            @Override
            public void run() {

            }
        }
    }

    String init(List<String> list) {
        return list.get(0);
    }

    ;

    public static void main(String[] args) throws InterruptedException {
        test1();
    }
}


/*
 * 线程start()方法执行顺序不等于启动顺序
 */
class Demo2 extends Thread {
    final Object object;

    public Demo2(int i, Object o) {
        super();
        this.i = i;
        this.object = o;
    }

    private int i;

    @Override
    public void run() {
        synchronized (object) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Object o = new Object();
        Demo2 t1 = new Demo2(1, o);
        Demo2 t2 = new Demo2(2, o);
        Demo2 t3 = new Demo2(3, o);
        Demo2 t4 = new Demo2(4, o);
        Demo2 t5 = new Demo2(5, o);
        Demo2 t6 = new Demo2(6, o);
        Demo2 t7 = new Demo2(7, o);
        Demo2 t8 = new Demo2(8, o);
        Demo2 t9 = new Demo2(9, o);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
    }

}
