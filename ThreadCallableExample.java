import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadCallableExample {
    private static final int FUTURE_TIMEOUT = 3000;
    private static final int SLEEP_DURATION = 5000;

    public static void main(String[] args) {
        MyCallable myCallable = new MyCallable();

        /*
         * thread doesn't accept callable. instead wrap callable in futuretask and give
         * it
         * to thread. futuretask extends both runnable and future and accepts both
         * runnable and callable argument
         */
        FutureTask<String> ft = new FutureTask<String>(myCallable);
        Thread t = new Thread(ft);
        t.start();
        String res = "";
        try {
            /*
             * get waits till callable returns result
             */
            // res = ft.get();

            /*
             * main thread will wait till future get()'s timeout, after that it will raise
             * timeout exception
             */
            res = ft.get(FUTURE_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException e) {
            /*
             * callable can throw exception while runnable can't which should be caught in
             * main thread
             */
            System.out.println(Thread.currentThread() + "---got exception---");
            e.printStackTrace();
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            System.out.println(Thread.currentThread() + "---got timeout exception---");
            e.printStackTrace();
        }
        System.out.println("got result from callable:" + res);
    }

    static class MyCallable implements Callable {
        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread() + "Inside callable");
            // throw new Exception("callable threw exception", null);
            Thread.sleep(ThreadCallableExample.SLEEP_DURATION);
            return "done";
        }
    }

}
