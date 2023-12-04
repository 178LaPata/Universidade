import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Warehouse2 {
    private Map<String, Product> map =  new HashMap<String, Product>();
    ReentrantLock wareLock = new ReentrantLock();

    private class Product {
        int quantity = 0;
        Condition prodCondition = wareLock.newCondition();
    }

    private Product get(String item) {
        this.wareLock.lock();
        Product p = map.get(item);
        if (p != null) return p;
        p = new Product();
        map.put(item, p);
        this.wareLock.unlock();
        return p;
    }

    public void supply(String item, int quantity) {
        this.wareLock.lock();
        try {
            Product prod = get(item);
            prod.quantity += quantity;
            prod.prodCondition.signalAll();
        } finally {
            this.wareLock.unlock();
        }
    }

    // Errado se faltar algum produto...
    public void consume(Set<String> items) {
        this.wareLock.lock();
        try{
            for (String item : items) {
                Product prod = get(item);
                while (prod.quantity <= 0) {
                    prod.prodCondition.await();
                }
                prod.quantity--;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            this.wareLock.unlock();
        }

    }
    public static void main(String[] args) {
        Warehouse2 warehouse = new Warehouse2();

        // Create a thread for supplying items.
        Thread supplierThread = new Thread(() -> {
            try {
                warehouse.supply("item1", 5);
                System.out.println("Supplier has supplied 5 item1.");
                Thread.sleep(100); // Simulate time taken to supply
                warehouse.supply("item2", 10);
                System.out.println("Supplier has supplied 10 item2.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Create a thread for consuming a set of items.
        Thread consumerThread1 = new Thread(() -> {
            System.out.println("Consumer 1 is waiting for item1 and item2.");
            warehouse.consume(new HashSet<>(Arrays.asList("item1", "item2")));
            System.out.println("Consumer 1 has consumed item1 and item2.");
        });

        // Create another thread for consuming a different set of items.
        Thread consumerThread2 = new Thread(() -> {
            System.out.println("Consumer 2 is waiting for item1.");
            warehouse.consume(new HashSet<>(Collections.singletonList("item1")));
            System.out.println("Consumer 2 has consumed item1.");
        });

        // Start all the threads.
        supplierThread.start();
        consumerThread1.start();
        consumerThread2.start();

        // Wait for all threads to finish execution.
        try {
            supplierThread.join();
            consumerThread1.join();
            consumerThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Print final quantities to see what's left in the warehouse.
        System.out.println("Final quantity of item1: " + warehouse.get("item1").quantity);
        System.out.println("Final quantity of item2: " + warehouse.get("item2").quantity);
    }
}
