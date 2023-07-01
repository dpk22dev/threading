public class ThreadWaitNotifyTest {
        public static void main(String [] args) throws InterruptedException {
      ThreadB b = new ThreadB();
      b.start();
      synchronized(b) {
                System.out.println("Main: Waiting for b to complete...");
                b.wait();
                System.out.println("Main: after wait, resuming");
                System.out.println("Main: Total is: " + b.total);

      }
    }


    static class ThreadB extends Thread {
    int total;

    public void run() {
    synchronized(this) {
        for(int i = 0; i < 100; i++) {
            total += i;
        }
        notify();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("ThreadB: woke up after sleeping in synchronized");
    }

    try {
            sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("ThreadB: woke up after sleeping outside synchronized");

    }
    }

}
