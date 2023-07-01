import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSynchronizedLock {
    private static int NT = 10000;
    private static int SLEEP = 10;

    public static void main(String[] args) {

        Instant startTime = Instant.now();
        Object lock = new Object();
        Counter counter = new Counter();
        Thread threads[] = new Thread[NT];
        for (int i = 0; i < NT; i++) {
            threads[i] = new Thread(new Producer(counter, lock), "thread" + i);
        }
        for (int i = 0; i < NT; i++) {
            threads[i].start();
        }
        for (int i = 0; i < NT; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("finally counter" + counter.getCount());
        Instant endTime = Instant.now();
        Duration executionTime = Duration.between(startTime, endTime);
        System.out.println("Execution time: " + executionTime.toMillis() + " milliseconds");

    }

    static class Producer implements Runnable {
        volatile Counter counter;
        Object lock;

        Producer(Counter c, Object lock) {
            this.counter = c;
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " -> counter before inc : " + counter.getCount());
                counter.increment();
                // if we sleep holding lock, each thread runs sequentially
                // try {
                // Thread.sleep(SLEEP);
                // } catch (InterruptedException e) {
                // // TODO Auto-generated catch block
                // e.printStackTrace();
                // }
                System.out.println(Thread.currentThread().getName() + " -> counter after inc: " + counter.getCount());
                System.out.println(Thread.currentThread().getName() + " -> counter before dec : " + counter.getCount());
                counter.decrement();
                System.out.println(Thread.currentThread().getName() + " -> counter after dec: " + counter.getCount());
            }

        }
    }

    static class Counter {
        private int count = 0;

        public int getCount() {
            return count;
        }

        public void increment() {
            count++;
        }

        public void decrement() {
            count--;
        }
    }

}
