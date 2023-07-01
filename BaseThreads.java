import org.w3c.dom.css.Counter;

public class BaseThreads {
    private static int NT = 2;

    public static void main(String[] args) {

        Thread threads[] = new Thread[NT];
        for (int i = 0; i < NT; i++) {
            threads[i] = new Thread("thread" + i);
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
    }
}
