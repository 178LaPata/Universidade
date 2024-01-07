package ep_especial19_20;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

interface ISalaDeEspera {
    boolean espera(String nome);
    void desiste(String nome);
    List<String> atende(int n);
}

class SalaDeEspera {
    private List<String> esperar;
    private Map<String, Boolean> resultados; // true se for atendido false se desistir
    private ReentrantLock espera_lock;
    private Map<String, Condition> condicao;


    public SalaDeEspera(){
        esperar = new ArrayList<>();
        resultados = new HashMap<>();
        espera_lock = new ReentrantLock();
        condicao = new HashMap<>();
    }

    boolean espera(String nome) throws InterruptedException{
        espera_lock.lock();
        try{
            resultados.put(nome, null);
            while(resultados.get(nome) == null){
                esperar.add(nome);
                Condition con = espera_lock.newCondition();
                condicao.put(nome, con);
                con.await();
            }
            return resultados.remove(nome);
        } finally{
            espera_lock.unlock();
        }
    }

    void desiste(String nome){
        espera_lock.lock();
        try{
            resultados.put(nome, true);
            condicao.get(nome).signal();
            esperar.remove(nome);
            condicao.remove(nome);
        } finally{
            espera_lock.unlock();
        }
    }

    List<String> atende(int n){
        espera_lock.lock();
        try{
            List<String> atendidos = new ArrayList<>();
            while (n>0){
                String remover = esperar.remove(0);
                resultados.put(remover, true);
                condicao.get(remover).signal();
                condicao.remove(remover);
                atendidos.add(remover);
            }
            return atendidos;
        } finally {
            espera_lock.unlock();
        }

    }
}
