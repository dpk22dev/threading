
/*
 * Runnable doesn't return value similar to futuretask. if we want we have to implement using wait, notify
 *
 */

import java.util.Random;
import java.util.random.RandomGenerator;

public class ThreadRunnableExample {

    private static final int NUM_THREADS = 5;

    public static void main(String[] args) {

        MyRunnable myRunnable[] = new MyRunnable[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            myRunnable[i] = new MyRunnable();
            Thread t = new Thread(myRunnable[i]);
            t.start();
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            Integer num = -1;
            try {
                num = (Integer) myRunnable[i].get();
            } catch (InterruptedException e) {
                // runnables can throw interrupted exceptions
                System.out.println(Thread.currentThread() + "got interrupted exception");
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " got :" + num);
        }

    }

    static class MyRunnable implements Runnable {
        Integer num;

        @Override
        public void run() {
            Random generator = new Random();
            Integer generatedInteger;
            generatedInteger = generator.nextInt(5);
            System.out.println(Thread.currentThread() + " generated" + generatedInteger);
            try {
                Thread.sleep(generatedInteger * 1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            num = generatedInteger;

            /*
             * don't forget to notify waiting threads inside synch block
             * main thread keeps on blocking on this
             */
            /*
             * point to note here is runnable thread is notifying other threads. in this
             * example to main thread
             */
            synchronized (this) {
                System.out.println(Thread.currentThread() + " notifying all threads");
                notifyAll();
            }

        }

        /*
         * Exception in thread "main" java.lang.IllegalMonitorStateException: current
         * thread is not owner
         * if you forget to add synchronized in get method declaration
         * 
         * point to note here is main thread calls get method, so main thread will be
         * put into wait and will be notfied here
         * 
         */
        public synchronized Object get() throws InterruptedException {
            while (null == num) {
                System.out.println(Thread.currentThread() + " waiting for result");
                wait();
            }
            System.out.println(Thread.currentThread() + " returning result" + num);
            return num;
        }

        public Object get(long timeoutMS) throws InterruptedException {
            while (null == num) {
                wait(timeoutMS);
            }
            return num;
        }

    }

}
