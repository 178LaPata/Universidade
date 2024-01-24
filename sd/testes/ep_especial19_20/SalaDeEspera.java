package ep_especial19_20;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import ISalaDeEspera;

interface ISalaDeEspera {
    boolean espera(String nome);
    void desiste(String nome);
    List<String> atende(int n);
}


class SalaDeEspera implements ISalaDeEspera{
    private Map<String, Boolean> pessoas_espera;
    private ReentrantLock l;
    private Condition espera;

    public SalaDeEspera (){
        this.pessoas_espera = new HashMap<>();
        this.l = new ReentrantLock();
        this.espera = l.newCondition();
    }


    public boolean espera(String nome){
        l.lock();
        try{
            pessoas_espera.put(nome,null);
            if(pessoas_espera.get(nome)==null){
                espera.await();
            }
            return pessoas_espera.remove(nome);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            l.unlock();
        }
    }

    public void desiste(String nome){
        l.lock();
        try{
            pessoas_espera.put(nome,false);
            espera.signalAll();
        }
        finally {
            l.unlock();
        }
    }
    public List<String> atende(int n){
        l.lock();
        try{
            List<String> atendidos = new ArrayList<>();
            for (int i = 0; i<n; i++){
                Set<String> nomes = (Set<String>) pessoas_espera;
                for (String nome : nomes){
                    if(pessoas_espera.get(nome)==null){
                        pessoas_espera.put(nome,true);
                        espera.signalAll();
                        atendidos.add(nome);
                    }
                }

            }
            return atendidos;
        }
        finally {
            l.unlock();
        }
    }

}
