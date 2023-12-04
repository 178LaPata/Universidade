package ex1;

public class Main {
    public static void main(String[] args) throws InterruptedException{
       int N = 10;
       Thread[] threads = new Thread[N];
         for(int i=0; i<N; i++) {
              threads[i] = new Thread(new Increment());
              threads[i].setName("Thread "+i);
         }
         for(int i=0; i<N; i++) {
              threads[i].start();
         }
         for(int i=0; i<N; i++) {
                threads[i].join();
         }
         System.out.println("Done");
    }
}
