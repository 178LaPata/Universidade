import java.util.Map;
import java.util.Set;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class fitness implements Serializable{
    private Map<String, utilizador> utilizadores;
    
    public fitness(){
        this.utilizadores = new HashMap<>();
    }

    public Map<String, utilizador> getUtilizadores(){
        Map<String, utilizador> aux = new HashMap<>();
        for(utilizador u : this.utilizadores.values()){
            aux.put(u.getEmail(), u.clone());
        }
        return aux;
    }

    public void setUtilizadores(Map<String, utilizador> utilizadores){
        this.utilizadores = new HashMap<>();
        for(utilizador u : utilizadores.values()){
            this.utilizadores.put(u.getEmail(), u.clone());
        }
    }

    public void adicionaUtilizador(utilizador u){
        this.utilizadores.put(u.getEmail(), u);
    }

    public boolean existeUtilizador(String email){
        utilizador user = this.utilizadores.get(email);
        if(user != null){
            return this.utilizadores.get(email).equals(user);
        }
        return false;
    }

    public int quantos(){
        return this.utilizadores.size();
    }

    public int quantos(String actividade, String email){
        int count = 0;
        if(this.existeUtilizador(email)){
            for(atividade a : this.utilizadores.get(email).getAtividades()){
                if(a.getClass().getSimpleName().equals(actividade)){
                    count++;
                }
            }
        }
        return count;
    }

    public utilizador getUtilizador(String email){
        return this.utilizadores.get(email);
    }

    public void adiciona(String email, atividade act){
        if(this.existeUtilizador(email)){
            this.utilizadores.get(email).adicionaAtividade(act);
        }
    }

    public List<atividade> getAllActividades(){
        List<atividade> all = new ArrayList<>();
        for(utilizador u : this.utilizadores.values()){
            for(atividade a : u.getAtividades()){
                all.add(a);
            }
        }
        return all;
    }

    public void adiciona(String email, Set<atividade> activs){
        if(this.existeUtilizador(email)){
            for(atividade a : activs){
                this.utilizadores.get(email).adicionaAtividade(a);
            }
        }
    }

    public int tempoTotalUtilizador(String email){
        int total = 0;
        for(atividade a : this.utilizadores.get(email).getAtividades()){
            total += a.getMinutos();
        }
        return total;
    }

    //faltam as ultimas 3


}