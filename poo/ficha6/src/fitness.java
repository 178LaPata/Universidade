import java.time.LocalDate;
import java.util.*;
import java.io.*;

public class fitness implements Serializable, FazMetros{
    private List<utilizador> utilizadores;
    private List<atividade> atividades;

    
    public fitness(){
        this.utilizadores = new ArrayList<>();
    }

    public List<utilizador> getUtilizadores(){
        List<utilizador> aux = new ArrayList<>();
        for(utilizador u : this.utilizadores){
            aux.add(u.clone());
        }
        return aux;
    }

    public void setUtilizadores(List<utilizador> utilizadores){
        this.utilizadores = new ArrayList<>();
        for(utilizador u : utilizadores){
            this.utilizadores.add(u.clone());
        }
    }

    // ======================= FASE 1 =======================
    public void adicionaUtilizador(utilizador u) throws FitnessException{
        if(this.utilizadores.contains(u)){
            throw new FitnessException("Utilizador já existe!");
        } else {
            this.utilizadores.add(u.clone());
        }
    }

    public boolean existeUtilizador(String email) throws FitnessException{
        boolean existe = false;
        for(utilizador u : this.utilizadores){
            if(u.getEmail().equals(email)){
                existe = true;
                break;
            }
        }
        if (!existe) {
            throw new FitnessException("Utilizador não existe!");
        }
        return existe;
    }

    public int quantos(){
        return this.utilizadores.size();
    }

    public int quantos(String actividade, String email){
        int total = 0;
        for(utilizador u : this.utilizadores){
            if(u.getEmail().equals(email)){
                for(atividade a : u.getAtividades()){
                    if(a.getDescricao().equals(actividade)){
                        total++;
                    }
                }
            }
        }
        return total;
    }

    public utilizador getUtilizador(String email){
        utilizador u = new utilizador();
        for(utilizador user : this.utilizadores){
            if(user.getEmail().equals(email)){
                u = user;
            }
        }
        return u;
    }

    public void adiciona(String email, atividade act) throws FitnessException {
        for(utilizador u : this.utilizadores){
            if(this.existeUtilizador(email)){
                u.adicionaAtividade(act);
            }
        }
    }

    public List<atividade> getAllActividades(){
        List<atividade> all = new ArrayList<>();
        for(utilizador u : this.utilizadores){
            all.addAll(u.getAtividades());
        }
        return all;
    }

    public void adiciona(String email, Set<atividade> activs) throws FitnessException {
        if(this.existeUtilizador(email)){
            for(atividade a : activs){
                this.utilizadores.get(Integer.parseInt(email)).adicionaAtividade(a);
            }
        } else {
            throw new FitnessException("Utilizador não existe!");
        }
    }

    public int tempoTotalUtilizador(String email){
        int total = 0;
        for(utilizador u : this.utilizadores){
            if(u.getEmail().equals(email)){
                for(atividade a : u.getAtividades()){
                    total += a.getMinutos();
                }
            }
        }
        return total;
    }

    //faltam as ultimas 3

    public atividade actividadeMaisExigente(){
       List<atividade> all = this.getAllActividades();
       atividade a = new atividade();
       for (atividade act : all){
           if(act.getCalorias() > a.getCalorias()){
               a = act;
           }
       }
       return a;
    }

    // nao sei se esta direito
    public utilizador utilizadorMaisActivo(){
        utilizador u = new utilizador();
        for(utilizador user : this.utilizadores){
            if(user.getAtividades().size() > u.getAtividades().size()){
                u = user;
            }
        }
        return u;
    }

    // ======================= FASE 2 =======================
    public utilizador utilizadorComMaisRegistos(){
        utilizador u = new utilizador();
        for(utilizador user : this.utilizadores){
            if(user.getAtividades().size() > u.getAtividades().size()){
                u = user;
            } else if(user.getAtividades().size() == u.getAtividades().size()){
                LocalDate data = LocalDate.now();
                if(user.diferencaDias(data, user.getDataRegisto()) > u.diferencaDias(data, user.getDataRegisto())){
                    u = user;
                }
            }
        }
        return u;
    }

    //ordenar primeiro por ordem crescente do consumo de calorias
    //e depois por ordem alfabética do nome
    public Set<utilizador> ordenarUtilizadores(){


        return null;
    }


        // ======================= FASE 3 =======================
    /*
    pontos -> cada metro percorrido da x pontos
    corrida: pontos * distancia acumulada
    canoagem: 1.5 * vento
     */
    public int setTotalPontos() {
        return 0;
    }

    public int getTotalPontos() {
        return 0;
    }

    public void getTotalPontosAtividade(int totalPontos) {

    }

    // ======================= FASE 4 =======================

    /*
    public void outputToCSV() throws IOException, FitnessException{
        if(this.utilizadores.size()==0){
            System.out.println("Não existem utilizadores");
        }

        File file = new File("utilizadores.csv");
        FileOutputStream fos = new FileOutputStream(file);
        PrintWriter pw = new PrintWriter(fos);
        for(utilizador u : this.utilizadores){
            pw.println(utilizador.toString() + "\n");
        }
        pw.flush();
        pw.close();
        fos.flush();
        fos.close();
    }
*/
    // funcoes da aula
    public void outputToObject() throws FitnessException, IOException{
        if(this.utilizadores.size()==0){
            throw new FitnessException("Não existem utilizadores");
        }
        FileOutputStream file = new FileOutputStream("utilizadores.dat");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(this.utilizadores);
        out.flush();
        out.close();
        file.flush();
        file.close();

    }

    public void inputToObject(String location) throws FitnessException, IOException, ClassNotFoundException {
        if(this.utilizadores.size()==0){
            throw new FitnessException("Não existem utilizadores");
        }
        FileInputStream file = new FileInputStream(location);
        ObjectInputStream out = new ObjectInputStream(file);
        this.utilizadores = (List<utilizador>) out.readObject();
        out.close();
        file.close();

    }


}