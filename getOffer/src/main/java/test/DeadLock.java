package test;

public class DeadLock {
    public static void main(String[] args) {
         final Object o1 = new Object();
         final Object o2 = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o1){
                    Thread.yield();
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    synchronized (o2){
                        System.out.println("1-fin");
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o2){
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    synchronized (o1){
                        System.out.println("2-fin");
                    }
                }
            }
        }).start();
        System.out.println("ok");
    }
}
