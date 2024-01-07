package ep_especial20_21;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ControloVacinas {
    private int nr_ready;
    private int nr_frascos;
    private int nr_vacinadas;
    private int NUM;
    private ReentrantLock vacina_Lock = new ReentrantLock();
    private Condition esperar = vacina_Lock.newCondition();

    public void pedirParaVacinar() throws InterruptedException{
        vacina_Lock.lock();
        try{
            nr_ready++;
            if(nr_ready >= NUM && nr_frascos > 0) esperar.signalAll();
            while(nr_ready < NUM && nr_frascos == 0) esperar.await();
            nr_ready--;
            nr_vacinadas++;
            if(nr_vacinadas == NUM){
                nr_frascos--;
                nr_vacinadas=0;
            }
        } finally{
            vacina_Lock.unlock();
        }
    }
    
    public void fornecerFrascos(int frascos){
        vacina_Lock.lock();
        try{
            nr_frascos += frascos;
            esperar.signalAll();
        } finally{
            vacina_Lock.unlock();
        }
    }
}
