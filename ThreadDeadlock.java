public class ThreadDeadlock {

    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        ThreadA threadA = new ThreadA(obj1, obj2);
        ThreadB threadB = new ThreadB(obj1, obj2);
        threadA.start();
        threadB.start();

    }
    
    static class ThreadA extends Thread{
        Object obj1, obj2;
        ThreadA( Object obj1, Object obj2 ){
            this.obj1 = obj1;
            this.obj2 = obj2;
        }
        @Override
        public void run() {
            synchronized( obj1 ){
                System.out.println("thread A: synched on obj1");
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("thread A: now going to synch on obj2");
                synchronized( obj2 ){
                    System.out.println("thread A: synched on obj2");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class ThreadB extends Thread{
        Object obj1, obj2;
        ThreadB( Object obj1, Object obj2 ){
            this.obj1 = obj1;
            this.obj2 = obj2;
        }
        @Override
        public void run() {
            synchronized( obj2 ){
                System.out.println("thread B: synched on obj2");
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("thread B: now going to synch on obj1");
                synchronized( obj1 ){
                    System.out.println("thread B: synched on obj1");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
