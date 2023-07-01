public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        ThreadB b = new ThreadB();
        b.start();
        System.out.println("Main: going to sleep");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        b.stopThread();
    }

    static class ThreadB extends Thread {
        private volatile boolean stop = false;

        public void stopThread() {
            this.stop = true;
        }

        public void run() {
            synchronized (this) {
                while (!stop) {
                    System.out.println("ThreadB: going to sleep with stop:" + stop);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("ThreadB: woke up after sleeping");
                }

            }
            System.out.println("ThreadB: exiting");

        }
    }

}
