package recurso21_22;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Reuniao {
    private ArrayList<Integer> listas_sala = new ArrayList<>();
    private int nr_total; // max de listas na sala
    private ArrayList<Integer> listas_espera = new ArrayList<>();
    private ReentrantLock lock_reuniao = new ReentrantLock();
    private Condition sala_cheia = lock_reuniao.newCondition();

    public void participa(int lista) throws InterruptedException{
        lock_reuniao.lock();
        try {
            while (listas_sala.size() == nr_total){
                listas_espera.add(lista);
                sala_cheia.await();
            }
            listas_sala.add(lista);
            listas_espera.remove(lista);
        } finally {
            lock_reuniao.unlock();
        }
    } 

    public void abandona(int lista){
        lock_reuniao.lock();
        try {
            listas_sala.remove(lista);
            sala_cheia.signal();
        } finally {
            lock_reuniao.unlock();
        }
    }

    public int naSala(){
        lock_reuniao.lock();
        try{
            return listas_sala.size();
        } finally {
            lock_reuniao.lock();
        }
    }

    int aEspera(){
        lock_reuniao.lock();
        try{
            return listas_espera.size();
        } finally {
            lock_reuniao.lock();
        }    
    }
}
