import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Barrier2 {
    private ReentrantLock lock;
    private Condition condition;
    private int calls;
    private int N;
    private int round;

    Barrier2(int N) {
        lock = new ReentrantLock();
        condition = lock.newCondition();
        this.N = N;
        calls = 0;
        round = 0;
    }

    void await() throws InterruptedException {
        lock.lock();
        try {
            int roundAtual = round;
            calls++;
            if (calls < N) while (round == roundAtual) condition.await();
            else{
                calls=0;
                round++;
                condition.signalAll();;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final int numberOfThreads = 5;
        Barrier b = new Barrier(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " at the barrier");
                    b.await();
                    System.out.println(Thread.currentThread().getName() + " passed the barrier");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
}


