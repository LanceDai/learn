package test;


import java.util.concurrent.atomic.AtomicInteger;

public class ProducerAndConsumer2 {
    private static final AtomicInteger COUNT = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Producer(), "producer").start();
        new Thread(new Consumer(), "consumer").start();
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            while (COUNT.get() < 100) {
                synchronized (COUNT) {
                    try {
                        COUNT.notify();
                        System.out.println(Thread.currentThread().getName() + " produce " + COUNT.getAndIncrement());
                        COUNT.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (COUNT) {
                COUNT.notify();
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (COUNT.get() < 100) {
                synchronized (COUNT) {
                    try {
                        COUNT.notify();
                        System.out.println(Thread.currentThread().getName() + " consume " + COUNT.getAndIncrement());
                        COUNT.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (COUNT) {
                COUNT.notify();
            }
        }
    }
}


