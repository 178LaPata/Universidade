package teste21_22;

import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Votacao {
    private Map<Integer, Boolean> identidade;
    private int cabines;
    private Map<Integer, Boolean> cabines_ocupadas;
    private Boolean votacao_aberta = true;
    private Map<Integer, Integer> votos;
    private ReentrantLock votacao_lock = new ReentrantLock();
    private Condition cabine_cheia = votacao_lock.newCondition();

    public boolean verifica(int identidade){
        votacao_lock.lock();
        try{
            if(this.identidade.get(identidade) == Boolean.TRUE){
                this.identidade.put(identidade, false);
                return true;
            }
            else{
                return false;
            }
        } finally{
            votacao_lock.unlock();
        }
    }

    public int firstFreeCabine(){
        for(int i = 0; i < cabines; i++){
            if(cabines_ocupadas.get(i) == false){
                return i;
            }
        }
        return -1;
    }

    public int esperaPorCabine(){
        votacao_lock.lock();
        try{
            while(cabines == cabines_ocupadas.size()){
                cabine_cheia.await();
            }
            return firstFreeCabine();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            cabines_ocupadas.put(firstFreeCabine(), true);
            votacao_lock.unlock();
        }
    }

    void vota(int escolha){
        votacao_lock.lock();
        try{
            if(votacao_aberta == true){
                votos.put(escolha, votos.get(escolha) + 1);
            }
        } finally {
            votacao_lock.unlock();
        }
    }

    void desocupaCabine(int i){
        votacao_lock.lock();
        try{
            cabines_ocupadas.put(1, false);
            cabine_cheia.signal();
        } finally {
            votacao_lock.unlock();
        }        
    }
    int vencedor(){
        votacao_lock.lock();
        try{
            votacao_aberta = false;
            int max = 0;
            int vencedor = 0;
            for(Map.Entry<Integer, Integer> entry : votos.entrySet()){
                if(entry.getValue() > max){
                    max = entry.getValue();
                    vencedor = entry.getKey();
                }
            }
            return vencedor;
        } finally {
            votacao_lock.unlock();
        }
    }
}
