package ep_especial22_23;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class HPC {
    private int nr_cores;
    private Map<Integer, Long> tarefas_duracao = new HashMap<>();
    private Map<Integer, Integer> tarefas_execucao = new HashMap<>();
    private int id_tarefa = 1;
    private ReentrantLock core_lock = new ReentrantLock();
    private Condition cores_necessarios = core_lock.newCondition();

    public int inicio(int ncores) throws InterruptedException{
        core_lock.lock();
        try{
            if(nr_cores < ncores) cores_necessarios.await();
            nr_cores -= ncores;
            tarefas_execucao.put(id_tarefa, ncores);
            id_tarefa++;
            return id_tarefa-1;
        } finally{
            core_lock.unlock();
        }
    }

    public void fim(int tarefa, long tempo){
        core_lock.lock();
        try{
            int cores_libertados = tarefas_execucao.get(tarefa);
            nr_cores += cores_libertados;
            cores_necessarios.signalAll();
            tarefas_duracao.put(tarefa,tempo);
            tarefas_execucao.remove(tarefa);
        } finally{
            core_lock.unlock();
        }
    }

    public Map<Integer, Long> historia(){
        core_lock.lock();
        try{
            return tarefas_duracao;
        } finally{
            core_lock.unlock();
        }
    }
}
