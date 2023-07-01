public class ThreadSelfDeadlock {
    public static void main(String[] args) {
        Object obj1 = new Object();
        synchronized( obj1 ){
            System.out.println("main: synced on obj1");
            synchronized( obj1 ){
                System.out.println("main: synced on obj1 again");
    
            }
        }
    }    
}
