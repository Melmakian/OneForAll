import java.util.ArrayList;
import java.util.List;

public class OneForAll {
    public static byte countThreads =3;
    static List<Thread> threads = new ArrayList<>(countThreads);

    public static void main(String[] args) throws InterruptedException {
            initThreadAndStart();
            Thread.sleep(3000);
            ourInterruptedMethod();
    }
    public static void ourInterruptedMethod(){
        for (Thread x : threads){
            x.interrupt();
        }
    }
    private static void initThreadAndStart(){
        Water water = new Water("water");
        for (int i = 0; i <countThreads ; i++) {
            threads.add(new Thread(water, "#"+i));
        }
        for (int i = 0; i <countThreads ; i++) {
            threads.get(i).start();
        }

    }
    public static class Water implements Runnable{
        private String commonResource;

        public Water(String commonResource) {
            this.commonResource = commonResource;
        }

        @Override
        public void run() {
            Thread cuurentThread = Thread.currentThread();
            boolean isCurentThreadInterrupted = cuurentThread.isInterrupted();
            String threadName = cuurentThread.getName();

            try {
                while (!isCurentThreadInterrupted){
                    System.out.println("Object "+ commonResource+ " thread "+ threadName);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {

            }
        }
    }
}
