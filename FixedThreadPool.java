import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FixedThreadPool {
    private static int NUM_THREADS = 1000;
public static void main(String[] args) {
    //String names[] = {"one", "two", "three", "four", "five"};
    //List<ThreadA> list = Arrays.stream(names).map( ThreadA::new ).collect( Collectors.toList() );

    //List<ThreadA> list = IntStream.of(NUM_THREADS).map( String::valueOf).map( ThreadA::new ).collect( Collectors.toList() );
    //IntStream.of(NUM_THREADS).map( i -> {return new String("thread"+i)} ).collect( Collectors.toList());

    List<ThreadA> list = IntStream.rangeClosed(1, NUM_THREADS)
                .mapToObj(num -> "thread" + num).map( ThreadA::new ).collect( Collectors.toList() );                               
    ExecutorService pool = Executors.newFixedThreadPool(3);

    list.stream().forEach( task -> pool.execute(task) );

    pool.shutdown();

}
    
static class ThreadA implements Runnable{
    int sleeptime = 200;
    String name;
    ThreadA(String name){
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println(this.name + " running will sleep for "+ sleeptime + " sec");
        try {
            Thread.sleep(sleeptime);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println( this.name + "woke up after" + sleeptime);
    }
}

}
