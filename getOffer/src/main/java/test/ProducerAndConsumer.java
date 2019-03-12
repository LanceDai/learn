package test;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerAndConsumer {
    private static final Queue<Integer> QUEUE = new ArrayDeque<Integer>();
    private static final int SIZE = 1;
    private static final AtomicInteger COUNT = new AtomicInteger(0);
    private static final int TIMES = 100;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1; i++) {
            Thread tmp = new Thread(new Producer(), "producer-" + i);
            tmp.start();
        }
        for (int i = 0; i < 1; i++) {
            Thread tmp = new Thread(new Consumer(), "consumer-" + i);
            tmp.start();
        }
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            while (COUNT.get() < TIMES) {
                synchronized (QUEUE) {
                    try {
                        if (QUEUE.size() >= SIZE) {
                            System.out.println(Thread.currentThread().getName() + " is wait");
                            QUEUE.wait();
                        }
                        System.out.println(Thread.currentThread().getName() + " produce " + COUNT.get());
                        QUEUE.add(COUNT.getAndIncrement());
                        QUEUE.notify();
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
            while (!QUEUE.isEmpty() || COUNT.get() < TIMES) {
                synchronized (QUEUE) {
                    try {
                        if (QUEUE.isEmpty()) {
                            System.out.println(Thread.currentThread().getName() + " is wait");
                            QUEUE.wait();
                        }
                        System.out.println(Thread.currentThread().getName() + " consume " + QUEUE.remove());
                        QUEUE.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


