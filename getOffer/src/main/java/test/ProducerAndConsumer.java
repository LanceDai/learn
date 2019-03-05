package test;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumer {
    private static final Queue<Integer> queue = new ArrayDeque<Integer>();
    private static final int size = 10;
    private static final AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread tmp = new Thread(new Producer(), "producer-" + i);
            tmp.start();
//            tmp.join();
        }
        for (int i = 0; i < 1; i++) {
            Thread tmp = new Thread(new Consumer(), "consumer-" + i);
            tmp.start();
//            tmp.join();
        }
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            while (count.get() < 100) {
                synchronized (queue) {
                    try {
                        if (queue.size() >= size) {
                            System.out.println(Thread.currentThread().getName() + " is wait");
                            queue.wait();
                        }
                        queue.add(count.getAndIncrement());
                        System.out.println(Thread.currentThread().getName() + " produce " + count.get());
                        queue.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (!queue.isEmpty() || count.get() < 100) {
                synchronized (queue) {
                    try {
                        if (queue.isEmpty()) {
                            System.out.println(Thread.currentThread().getName() + " is wait");
                            queue.wait();
                        }
                        System.out.println(Thread.currentThread().getName() + " consume " + queue.remove());
                        queue.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


