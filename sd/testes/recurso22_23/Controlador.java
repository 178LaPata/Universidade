package recurso22_23;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Controlador {
    private int nKarts;
    private int availableKarts; 
    private boolean[] kartsReady = new boolean[nKarts];
    private Map<Integer, Integer> voltasComp = new HashMap<>(); // <kart, voltas>
    private ReentrantLock lock_controlador = new ReentrantLock();
    private Condition reserva = lock_controlador.newCondition();
    private Condition preparado = lock_controlador.newCondition();
    private Condition corridaAcabou = lock_controlador.newCondition();
    private int vencedor = -1;

    public int reserva() throws InterruptedException {
        lock_controlador.lock();
        try{
            while(availableKarts == 0){
                reserva.await();
            }
            kartsReady[availableKarts] = false;
            return availableKarts;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            availableKarts--;
            lock_controlador.unlock();
        }
        return -1;
    }

    public boolean isReady(){
        for(int i = 0; i < nKarts; i++){
            if(!kartsReady[i]){
                return false;
            }
        }
        return true;
    }

    public void preparado(int kart) throws InterruptedException {
        lock_controlador.lock();
        try{
            kartsReady[kart] = true;
            if(isReady()){
                preparado.signalAll();
            }
            while(!isReady()){
                preparado.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock_controlador.unlock();
        }
    }

    public void completaVolta(int kart){
        lock_controlador.lock();
        try{
            voltasComp.put(kart, voltasComp.get(kart) + 1);
            if(voltasComp.get(kart) == 10){
                corridaAcabou.signalAll();
            }
        } finally {
            lock_controlador.unlock();
        }
    }

    public int[] voltasCompletas(){
        lock_controlador.lock();
        try{
            int[] voltas = new int[nKarts];
            for(int i = 0; i < nKarts; i++){
                voltas[i] = voltasComp.get(i);
            }
            return voltas;
        } finally {
            lock_controlador.unlock();
        }
    }

    public int vencedor() throws InterruptedException{
        lock_controlador.lock();
        try{
            while(vencedor == -1){
                corridaAcabou.await();
            }
            return vencedor;
        } finally {
            for (int i = 0; i < nKarts; i++) {
                kartsReady[i] = false;
                voltasComp.put(i, 0);
            }
            vencedor = -1;
            availableKarts = nKarts;
            lock_controlador.unlock();
        }
    }
}
