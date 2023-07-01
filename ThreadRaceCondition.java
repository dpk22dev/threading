public class ThreadRaceCondition {

    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread t1 = new Thread(new MyRunnable(counter), "t1");
        Thread t2 = new Thread(new MyRunnable(counter), "t2");
        Thread t3 = new Thread(new MyRunnable(counter), "t3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("after finish counter" + counter.getCount());
    }

    static class MyRunnable implements Runnable {

        volatile Counter counter;

        MyRunnable(Counter c) {
            this.counter = c;
        }

        @Override
        public void run() {
            System.out
                    .println(Thread.currentThread().getName() + ": before increment counter -> " + counter.getCount());
            counter.increment();
            System.out
                    .println(Thread.currentThread().getName() + ": after increment  counter -> " + counter.getCount());
            // try {
            // Thread.sleep(1000);
            // } catch (InterruptedException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }
            System.out
                    .println(Thread.currentThread().getName() + ": before decrement counter -> " + counter.getCount());
            counter.decrement();
            System.out
                    .println(Thread.currentThread().getName() + ": after decrement  counter -> " + counter.getCount());

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
