package teste22_23;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Cache {
    private int max_size;
    private Map<Integer, byte[]> cache = new HashMap<>();
    private ReentrantLock cache_lock = new ReentrantLock();
    private Condition cache_full = cache_lock.newCondition();

    public void put(int key, byte[] value) throws InterruptedException{
        cache_lock.lock();
        try {
            if(cache.containsKey(key)){
                cache.put(key, value);
            } else {
                if(cache.size() == max_size){
                    cache_full.await();
                }
                cache.put(key, value);                
            }
        } finally {
            cache_lock.unlock();
        }
    }

    public byte[] get(int key){
        cache_lock.lock();
        try {
            if(cache.containsKey(key)){
                return cache.get(key);
            } else {
                return null;
            }
        } finally {
            cache_lock.unlock();
        }
    }

    public void evict(int key){
        cache_lock.lock();
        try{
            if(cache.containsKey(key)){
                cache.remove(key);
                cache_full.signal();
            }
        } finally {
            cache_lock.unlock();
        }
    }
}
