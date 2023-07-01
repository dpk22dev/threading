public class ThreadSchedulerTest {
    public static void main(String[] args) {

        ThreadB threadB = new ThreadB();
        threadB.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("Main: "+ i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


static class ThreadB extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("threadB: "+ i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

}
