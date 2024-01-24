
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class HPC {
    private int cores_livres;
    private Map<Integer, Long> tarefa_duracao;
    private Map<Integer, Integer> tarefa_execucao;
    private int id_tarefa;
    private ReentrantLock hpc_lock;
    private Condition hpc_con;

    public HPC(){
        this.cores_livres = 0;
        this.tarefa_duracao = new HashMap<>();
        this.tarefa_execucao = new HashMap<>();
        this.id_tarefa = 1;
        this.hpc_lock = new ReentrantLock();
        this.hpc_con = hpc_lock.newCondition();
    }

    int inicio(int ncores) throws InterruptedException{
        hpc_lock.lock();
        try{
            if(cores_livres < ncores) hpc_con.await();
            cores_livres -=ncores;
            tarefa_execucao.put(id_tarefa, ncores);
            id_tarefa++;
            return id_tarefa - 1;
        } finally{
            hpc_lock.unlock();
        }
    }
    void fim(int tarefa, long tempo){
        hpc_lock.lock();
        try{
            int cores = tarefa_execucao.get(tarefa);
            cores_livres += cores;
            hpc_con.signalAll();
            tarefa_duracao.put(tarefa,tempo);
            tarefa_execucao.remove(tarefa);
        } finally{
            hpc_lock.unlock();
        }

    }
    Map<Integer, Long> historia(){
        hpc_lock.lock();
        try{
            return tarefa_duracao;
        }finally{
            hpc_lock.unlock();
        }
    }
}
