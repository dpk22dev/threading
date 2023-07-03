import java.util.Random;

public class ThreadWaitInterrupt {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread t = new Thread(myRunnable);
        t.start();
        try {
            Integer res = (Integer) myRunnable.get();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName());
            e.printStackTrace();
        }
    }

    static class MyRunnable implements Runnable {
        Integer num = 1;

        @Override
        public void run() {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            synchronized (this) {
                System.out.println(Thread.currentThread() + " notifying all threads");
                notifyAll();
            }

        }

        public synchronized Object get() throws InterruptedException {
            while (null == num) {
                System.out.println(Thread.currentThread() + " waiting for result");
                wait();
            }
            System.out.println(Thread.currentThread() + " returning result" + num);
            return num;
        }

    }

}
