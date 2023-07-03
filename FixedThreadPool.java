import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * gives NUM_THREADS to threadpool with capacity CAPACITY, each thread sleeps for SLEEP_DURATION
 */

public class FixedThreadPool {
    private static final int CAPACITY = 3;
    private static int NUM_THREADS = 1000;

    public static void main(String[] args) {
        List<ThreadA> list = IntStream.rangeClosed(1, NUM_THREADS)
                .mapToObj(num -> "thread" + num).map(ThreadA::new).collect(Collectors.toList());
        ExecutorService pool = Executors.newFixedThreadPool(CAPACITY);

        list.stream().forEach(task -> pool.execute(task));

        pool.shutdown();

    }

    static class ThreadA implements Runnable {
        private static final int SLEEP_DURATION = 200;
        int sleeptime = SLEEP_DURATION;
        String name;

        ThreadA(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(this.name + " running will sleep for " + sleeptime + " sec");
            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(this.name + "woke up after" + sleeptime);
        }
    }

}
