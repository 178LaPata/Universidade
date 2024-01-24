import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Controlador {
    private int nr_karts; 
    private List<Boolean> reservados;
    private List<Integer> nr_voltas;
    private int nr_participantes;
    private List<Boolean> preparados;
    private int total_voltas;
    private ReentrantLock controlador_lock;
    private Condition controlador_con;  
    private Condition preparados_con;
    private Boolean corrida_curso;
    private Condition corrida_con;
    private int vencedor;
    private int count;

    public Controlador(){
        this.nr_karts = 10; 
        this.reservados = new ArrayList<>();
        this.nr_voltas = new ArrayList<>();
        this.nr_participantes = 0;
        this.preparados = new ArrayList<>();
        this.total_voltas = 10;
        this.controlador_lock = new ReentrantLock();
        this.controlador_con = controlador_lock.newCondition();
        this.preparados_con = controlador_lock.newCondition();
        this.corrida_curso = false;
        this.corrida_con = controlador_lock.newCondition();
        this.vencedor = -1;
        this.count = 0;
        for(int i = 0; i < nr_karts; i++){
            this.reservados.add(false);
            this.nr_voltas.add(0);
            this.preparados.add(false);
        }
    }

    int reserva() throws InterruptedException{
        controlador_lock.lock();
        try{
            while(!reservados.contains(false)){
                controlador_con.await();
            }
            int first_false = reservados.indexOf(false);
            reservados.set(first_false,true);
            return first_false;
        } finally {
            controlador_lock.unlock();
        }
    }

    void preparado(int kart) throws InterruptedException{
        controlador_lock.lock();
        try{
            preparados.set(kart,true);
            if(!preparados.contains(false)) preparados_con.signalAll();
            while(preparados.contains(false)){
                preparados_con.await();
            }
            corrida_curso = true;
        } finally{
            controlador_lock.unlock();
        }
    }

    void completaVolta(int kart){
        controlador_lock.lock();
        try{
            nr_voltas.set(kart, nr_voltas.get(kart) + 1);
            if(nr_voltas.get(kart) == total_voltas){
                corrida_curso = false;
                vencedor = kart;
            }
            if(corrida_curso == false){
                reservados.set(kart,false);
                preparados.set(kart,false);
                controlador_con.signal();
                count++;
                if(count == nr_karts){
                    corrida_con.signalAll();
                    count = 0;
                }
            }
        } finally{
            controlador_lock.unlock();
        }
    }

    int[] voltasCompletas(){
        controlador_lock.lock();
        try{
            int[] voltas = new int[nr_karts];
            for(int i = 0; i < nr_karts; i++){
                voltas[i] = nr_voltas.get(i);
            }
            return voltas;
        } finally{
            controlador_lock.unlock();
        }
    }
    

    int vencedor() throws InterruptedException{
        controlador_lock.lock();
        try{
            while(corrida_curso){
                corrida_con.await();
            }
            for(int i = 0; i < nr_karts; i++){
                nr_voltas.set(i,0);
            }
            return vencedor;
        } finally{
            controlador_lock.unlock();
        }
    }
}