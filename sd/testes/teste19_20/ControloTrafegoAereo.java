package teste19_20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ControloTrafegoAereo {
    private int num_pistas;
    private Map<Integer,Boolean> pistas_livres;
    private ArrayList<Condition> avioes_espera;
    private ReentrantLock cta_lock;

    public ControloTrafegoAereo(){
        this.num_pistas = 10;
        this.pistas_livres = new HashMap<>();
        for(int i=0; i<num_pistas; i++){
            pistas_livres.put(i,true);
        }
        this.avioes_espera = new ArrayList<>();
        this.cta_lock = new ReentrantLock();   
    }

    int pedirParaDescolar() throws InterruptedException{
        cta_lock.lock();
        int pista = -1;
        try{
            if(pistas_livres.containsValue(true)){
                for(int i=0; i<num_pistas; i++){
                    if(pistas_livres.get(i).equals(true)){
                        pista = i;
                        pistas_livres.put(i,false);
                    }
                }
            }
            Condition con = cta_lock.newCondition();
            avioes_espera.add(con);
            while(pista == -1){
                con.await();
            }
        } finally {
            cta_lock.unlock();
        }
        return pista;
    }

    int pedirParaAterrar() throws InterruptedException{
        cta_lock.lock();
        int pista = -1;
        try{
            if(pistas_livres.containsValue(true)){
                for(int i=0; i<num_pistas; i++){
                    if(pistas_livres.get(i).equals(true)){
                        pista = i;
                        pistas_livres.put(i,false);
                    }
                }
            }
            Condition con = cta_lock.newCondition();
            avioes_espera.add(con);
            while(pista == -1){
                con.await();
            }
        } finally {
            cta_lock.unlock();
        }
        return pista;
    }
    void descolou(int pista){
        cta_lock.lock();
        try{
            pistas_livres.put(pista,true);
            Condition con = avioes_espera.remove(0);
            con.signal();
        } finally {
            cta_lock.unlock();
        }
    }

    void aterrou(int pista){
        cta_lock.lock();
        try{
            pistas_livres.put(pista,true);
            Condition con = avioes_espera.remove(0);
            con.signal();
        } finally {
            cta_lock.unlock();
        }    
    }
}