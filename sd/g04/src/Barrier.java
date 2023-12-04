import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Barrier {
    private ReentrantLock lock;
    private Condition condition;
    private int calls;
    private int N;

    Barrier(int N) {
        lock = new ReentrantLock();
        condition = lock.newCondition();
        this.N = N;
        calls = 0;
    }

    void await() throws InterruptedException {
        lock.lock();
        try {
            calls++;
            if (calls < N) while (calls < N) condition.await();
            else condition.signalAll();
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

